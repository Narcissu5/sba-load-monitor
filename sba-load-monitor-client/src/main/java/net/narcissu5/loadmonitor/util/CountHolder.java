package net.narcissu5.loadmonitor.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 曾浩 on 2017/11/10.
 */
public class CountHolder {
    private static final int BUCKET_SIZE = 10;

    private CountInSecond[] left;
    private CountInSecond[] right;
    private CountInSecond[] current;

    public CountHolder() {
        left = new CountInSecond[BUCKET_SIZE];
        right = new CountInSecond[BUCKET_SIZE];
        current = left;
        long ts = System.currentTimeMillis();
        clearBucket(left, ts - 10000);
        clearBucket(right, ts);
    }

    public synchronized void flip() {
        if (current == right) {
            current = left;
            clearBucket(right, System.currentTimeMillis());
        } else {
            current = right;
            clearBucket(left, System.currentTimeMillis());
        }
    }

    public void incr(long timestamp) {
        int bucket = (int) (timestamp / 1000 / BUCKET_SIZE);
        current[bucket].incr();
    }

    public void clearBucket(CountInSecond[] buckets, long ts) {
        int mod = (int) (ts / 1000 / BUCKET_SIZE);
        int first = mod * 10 + 10;
        for (int idx = 0; idx < BUCKET_SIZE; idx++) {
            buckets[idx] = new CountInSecond(first++);
        }
    }

    protected enum Position {
        RIGHT, LEFT
    }

    public class CountInSecond {
        private final long timestamp;
        private AtomicInteger count;

        public CountInSecond(long timestamp) {
            this.timestamp = timestamp;
        }

        public void incr() {
            count.incrementAndGet();
        }
    }
}
