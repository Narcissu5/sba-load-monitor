package net.narcissu5.loadmonitor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import net.narcissu5.loadmonitor.dao.SbaLoad1MAggrDAO;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import net.narcissu5.loadmonitor.model.Load1M;
import net.narcissu5.loadmonitor.model.Load1MAggr;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 曾浩 on 2017/11/20.
 */
@RunWith(SpringRunner.class)
@JdbcTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SaveLoadServiceTest {

    @MockBean
    CloseableHttpClient httpClient;

    @MockBean
    EurekaClient eurekaClient;

    @Autowired
    SbaLoad1MDAO sbaLoad1MDAO;

    @Autowired
    SbaLoad1MAggrDAO sbaLoad1MAggrDAO;

    @Autowired
    SaveLoadService saveLoadService;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void init() throws IOException {
        InstanceInfo instanceInfo = new InstanceInfo("Test", "TestApp", "", "1.1.1.1", "", null, null, "",
                "", "", "", "", "", 0, null, "TestHost", InstanceInfo.InstanceStatus.UP, InstanceInfo.InstanceStatus.UP,
                null, false, null, System.currentTimeMillis(), System.currentTimeMillis(),
                InstanceInfo.ActionType.MODIFIED, "");
        Application app = new Application();
        app.addInstance(instanceInfo);
        Applications apps = new Applications(Collections.singletonList(app));
        when(eurekaClient.getApplications()).thenReturn(apps);

        LoadModel lm = new LoadModel();
        lm.setCount(100);
        lm.setMinute(4444);
        CloseableHttpResponse resp2 = mock(CloseableHttpResponse.class);
        when(resp2.getStatusLine())
                .thenReturn(new BasicStatusLine(new ProtocolVersion("http", 1, 1), HttpStatus.SC_OK, ""));
        when(resp2.getEntity()).thenReturn(new StringEntity(objectMapper.writeValueAsString(lm), "UTF-8"));
        when(httpClient.execute(anyObject())).thenReturn(resp2);
    }

    @Test
    public void recordSingleAppTest() throws IOException, URISyntaxException {
        saveLoadService.recordLoad();
        List<Load1M> loads = sbaLoad1MDAO.findByMinuteAndHostName(4444, "TestHost");
        Assert.assertEquals(1, loads.size());
        Load1M load = loads.get(0);
        Assert.assertEquals("TestApp", load.getAppName());
        Assert.assertEquals("TestHost", load.getHostName());
        Assert.assertEquals(100, load.getCount());
        Assert.assertEquals(4444, load.getMinute());
    }

    @Test
    public void recordAggrTest() throws IOException, URISyntaxException {
        saveLoadService.recordLoad();
        List<Load1MAggr> loads = sbaLoad1MAggrDAO.findByAppName("TestApp");
        Assert.assertEquals(1, loads.size());
        Load1MAggr load = loads.get(0);
        Assert.assertEquals("TestApp", load.getAppName());
        Assert.assertEquals(100, load.getCount());
        Assert.assertEquals(4444, load.getMinute());
    }
}
