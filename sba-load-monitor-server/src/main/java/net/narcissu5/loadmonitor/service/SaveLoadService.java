package net.narcissu5.loadmonitor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾浩 on 2017/11/17.
 */
public class SaveLoadService {
    private static final Logger logger = LoggerFactory.getLogger(SaveLoadService.class);

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    CloseableHttpClient httpClient;

    @Autowired
    ObjectMapper objectMapper;

    Map<String, LoadModel> currentApp;

    public Map<String, LoadModel> getCurrent() {
        return currentApp;
    }

    public void recordLoad() throws URISyntaxException, IOException {
        Map<String, LoadModel> currentApp = new HashMap<>();
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        for (Application application : applications) {
            List<InstanceInfo> instances = application.getInstances();
            for (InstanceInfo instanceInfo : instances) {
                URIBuilder builder = new URIBuilder();
                builder.setScheme("http");
                builder.setHost(instanceInfo.getIPAddr());
                builder.setPort(instanceInfo.getPort());
                builder.setPath("load");

                HttpGet get = new HttpGet(builder.build());
                HttpResponse resp = httpClient.execute(get);
                if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    logger.info("Fail to get load of {}, status code: {}",
                            instanceInfo.getHostName(), resp.getStatusLine().getStatusCode());
                } else {
                    LoadModel model = objectMapper.readValue(resp.getEntity().getContent(), LoadModel.class);
                    LoadModel exist = currentApp.get(instanceInfo.getAppName());
                    if (exist == null) {
                        currentApp.put(instanceInfo.getAppName(), model);
                    } else {
                        exist.add(model);
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug("Get load from {}@{}:{}",
                                instanceInfo.getAppName(), instanceInfo.getHostName(), model);
                    }
                }
            }
        }

        this.currentApp = currentApp;
    }
}