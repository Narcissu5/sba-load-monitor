package net.narcissu5.loadmonitor.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曾浩 on 2017/10/19.
 */
public class LoadMonitorInterceptor extends HandlerInterceptorAdapter {
    public static final int BUCKETS_SIZE = 8;
    public static final int MILL_ONE_MINUTE = 1000 * 60;

    private AtomicInteger[] buckets;

    public LoadMonitorInterceptor() {
        buckets = new AtomicInteger[BUCKETS_SIZE];
        for (int idx = 0; idx < BUCKETS_SIZE; idx++) {
            buckets[idx] = new AtomicInteger(0);
        }
    }

    public AtomicInteger getCount(int minute) {
        return buckets[minute & 7];
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        int minute = (int) (System.currentTimeMillis() / BUCKETS_SIZE);
        buckets[minute & 7].incrementAndGet();
    }
}
