package net.narcissu5.loadmonitor.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曾浩 on 2017/11/15.
 */
public class CountInMinute {
    final long minute;
    private AtomicInteger count;
    private AtomicInteger[] httpStatus;

    public CountInMinute(long minute) {
        this.minute = minute;
        this.count = new AtomicInteger(0);
        this.httpStatus = new AtomicInteger[6];
        for (int i = 0; i < 6; i++) {
            this.httpStatus[i] = new AtomicInteger(0);
        }
    }

    public void incr() {
        count.incrementAndGet();
    }

    public void incr(int httpStatusCode) {
        count.incrementAndGet();
        httpStatus[HttpStatusEnum.fromStatusCode(httpStatusCode).index].incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }

    public long getMinute() {
        return minute;
    }

    public AtomicInteger[] getHttpStatus() {
        return httpStatus;
    }
}
