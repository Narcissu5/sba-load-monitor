package net.narcissu5.loadmonitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.narcissu5.loadmonitor.util.CountHolder;
import net.narcissu5.loadmonitor.util.CountInMinute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;

/**
 * Created by 曾浩 on 2017/11/10.
 */
public class LoadEndPoint implements Endpoint {
    private static final Logger logger = LoggerFactory.getLogger(LoadEndPoint.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CountHolder countHolder;

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
        CountInMinute count = countHolder.getCount((System.currentTimeMillis() / 1000 / 60) - 1);
        try {
            return objectMapper.writeValueAsString(count);
        } catch (JsonProcessingException e) {
            logger.warn("Unexpected error", e);
            return null;
        }
    }
}
