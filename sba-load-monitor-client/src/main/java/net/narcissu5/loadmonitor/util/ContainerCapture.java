package net.narcissu5.loadmonitor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by 曾浩 on 2017/10/20.
 */
public class ContainerCapture {
    @Value("${spring.application.name}")
    String applicationName;

    int serverPort;

    String hostName;

    @Autowired
    Environment environment;

    public ContainerCapture() throws UnknownHostException {
        hostName = InetAddress.getLocalHost().getHostName();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getHostName() {
        return hostName;
    }

    public int getServerPort() {
        // race condition doesn't matter
        if(serverPort == 0) {
            serverPort = environment.getProperty("local.server.port", Integer.class);
        }

        return serverPort;
    }
}
