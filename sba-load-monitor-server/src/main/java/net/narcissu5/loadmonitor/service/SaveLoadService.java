package net.narcissu5.loadmonitor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import net.narcissu5.loadmonitor.dao.SbaLoad1MAggrDAO;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾浩 on 2017/11/17.
 */
@Service
public class SaveLoadService {
    private static final String PATH = "load";
    private static final String SCHEMA = "http";
    private static final int CONNECTION_TIMEOUT_MS = 1000;
    private static final Logger logger = LoggerFactory.getLogger(SaveLoadService.class);

    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    CloseableHttpClient httpClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SbaLoad1MDAO sbaLoad1MDAO;

    @Autowired
    SbaLoad1MAggrDAO sbaLoad1MAggrDAO;

    Map<String, LoadModel> currentApp;

    public Map<String, LoadModel> getCurrent() {
        return currentApp;
    }

    public void recordLoad() throws URISyntaxException, IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Begin to fetch load data");
        }

        Map<String, LoadModel> currentApp = new HashMap<>();
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        for (Application application : applications) {
            List<InstanceInfo> instances = application.getInstances();
            if (logger.isDebugEnabled()) {
                logger.debug("Found application:{},instance:{}", application.getName(), instances.size());
            }
            for (InstanceInfo instanceInfo : instances) {
                LoadModel model = getLoadModel(instanceInfo.getIPAddr(),
                        instanceInfo.getPort(), instanceInfo.getHostName());

                if (model != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Get load from {}@{}@{}:{}",
                                instanceInfo.getAppName(), instanceInfo.getHostName(), instanceInfo.getIPAddr(), model);
                    }
                    model.setAppName(instanceInfo.getAppName());

                    if (model.getMinute() == 0) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Get load from {}@{}:{}, but minute is zero",
                                    instanceInfo.getAppName(), instanceInfo.getHostName(), model);
                        }
                        continue;
                    }
                    LoadModel exist = currentApp.get(instanceInfo.getAppName());
                    if (exist == null) {
                        currentApp.put(instanceInfo.getAppName(), model);
                    } else {
                        exist.add(model);
                    }
                    sbaLoad1MDAO.insert(instanceInfo.getAppName(), instanceInfo.getHostName(), instanceInfo.getPort(),
                            model.getCount(), (int) model.getMinute(), model.getHttpStatus());
                }
            }
        }

        this.currentApp = currentApp;
        for (LoadModel loadModel : currentApp.values()) {
            sbaLoad1MAggrDAO.insert(loadModel.getAppName(), loadModel.getCount(),
                    (int) loadModel.getMinute(), loadModel.getHttpStatus());
        }
    }

    private LoadModel getLoadModel(String ipAddr, int port, String hostName) throws URISyntaxException {
        LoadModel model;

        URIBuilder builder = new URIBuilder();
        builder.setScheme(SCHEMA);
        builder.setHost(ipAddr);
        builder.setPort(port);
        builder.setPath(PATH);

        HttpGet get = new HttpGet(builder.build());
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT_MS)
                .setConnectTimeout(CONNECTION_TIMEOUT_MS)
                .setSocketTimeout(CONNECTION_TIMEOUT_MS)
                .build();
        get.setConfig(requestConfig);
        try (CloseableHttpResponse resp = httpClient.execute(get)) {
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.info("Fail to get load of {}@{}, status code: {}", hostName, ipAddr,
                        resp.getStatusLine().getStatusCode());
                return null;
            } else {
                try (InputStream is = resp.getEntity().getContent()) {
                    String loadStr = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
                    if (logger.isDebugEnabled()) {
                        logger.debug("Get load from {},{}", hostName, loadStr);
                    }
                    model = objectMapper.readValue(loadStr, LoadModel.class);
                } catch (IOException e) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Unexpected error when get load from " + hostName, e);
                    }
                    return null;
                }
            }
        } catch (IOException e) {
            logger.info("Error when execute request:{}:{}", get, e.getMessage());
            return null;
        }

        return model;
    }
}
