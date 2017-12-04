package net.narcissu5.loadmonitor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曾浩 on 2017/10/19.
 */
public class LoadMonitorInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    CountHolder countHolder;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        countHolder.incr(System.currentTimeMillis() / 1000 / 60, response.getStatus());
    }
}
