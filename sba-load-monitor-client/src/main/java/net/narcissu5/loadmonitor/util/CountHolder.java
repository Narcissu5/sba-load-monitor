package net.narcissu5.loadmonitor.util;

/**
 * Created by 曾浩 on 2017/11/10.
 */
public class CountHolder {
    private static final int BUCKET_SIZE = 16;
    private static final int BUFFER_SIZE = 64;

    private volatile CountInMinute[] buffer;

    public CountHolder() {
        buffer = new CountInMinute[BUFFER_SIZE];
        for (int idx = 0; idx < BUFFER_SIZE; idx++) {
            buffer[idx] = new CountInMinute(0);
        }
    }

    /**
     * increase counter
     *
     * @param minute epoch minute
     */
    public void incr(long minute, int httpStatusCode) {
        int pos = (int) (minute & 0x3F);
        if (buffer[pos].minute != minute) {
            synchronized (this) {
                if (buffer[pos].minute != minute) {
                    int begin = (pos & 0x7ffffff0);
                    long beginTs = (minute & 0x7ffffff0);
                    for (int idx = begin; idx < begin + BUCKET_SIZE; idx++) {
                        buffer[idx] = new CountInMinute(beginTs++);
                    }
                }
            }
        }
        buffer[pos].incr(httpStatusCode);
    }

    /**
     * getCount counter, only available in one minute
     *
     * @param minute in minute
     * @return
     */
    public CountInMinute getCount(long minute) {
        int pos = (int) (minute & 0x3F);
        CountInMinute ret = buffer[pos];
        if (ret.getMinute() == minute) {
            return ret;
        } else {
            return new CountInMinute(minute);
        }
    }
}
