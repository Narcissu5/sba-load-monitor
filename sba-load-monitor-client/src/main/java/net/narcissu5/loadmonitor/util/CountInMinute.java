package net.narcissu5.loadmonitor.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曾浩 on 2017/11/15.
 */
public class CountInMinute {
    final long minute;
    private AtomicInteger count;

    public CountInMinute(long minute) {
        this.minute = minute;
        count = new AtomicInteger(0);
    }

    public void incr() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public long getMinute() {
        return minute;
    }
}
