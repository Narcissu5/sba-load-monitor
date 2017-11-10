package net.narcissu5.loadmonitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

/**
 * Created by 曾浩 on 2017/11/10.
 */
public class LoadEndPoint implements Endpoint {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String getId() {
        return "load";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public Object invoke() {
        return "{}";
    }
}
