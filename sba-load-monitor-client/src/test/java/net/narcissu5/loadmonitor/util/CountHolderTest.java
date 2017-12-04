package net.narcissu5.loadmonitor.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by 曾浩 on 2017/11/15.
 */
public class CountHolderTest {
    @Test
    public void incrTest() {
        CountHolder ch = new CountHolder();
        ch.incr(1, 200);
        ch.incr(1, 200);
        Assert.assertEquals(2, ch.getCount(1).getCount());
    }

    @Test
    public void refreshTest() {
        CountHolder ch = new CountHolder();
        for (int idx = 0; idx < 300; idx++) {
            for (int idx2 = 0; idx2 < idx; idx2++) {
                ch.incr(idx, 200);
            }
        }

        Assert.assertEquals(255, ch.getCount(255).getCount());
        Assert.assertEquals(290, ch.getCount(290).getCount());
        Assert.assertEquals(299, ch.getCount(299).getCount());
    }

    @Test
    public void httpStatusCodeTest() {
        CountHolder ch = new CountHolder();
        ch.incr(1,101);
        ch.incr(1,200);
        ch.incr(1,204);
        ch.incr(1,300);
        ch.incr(1,400);
        ch.incr(1,501);
        ch.incr(1,700);
        Assert.assertEquals(1, ch.getCount(1).getHttpStatus()[1].get());
        Assert.assertEquals(2, ch.getCount(1).getHttpStatus()[2].get());
        Assert.assertEquals(1, ch.getCount(1).getHttpStatus()[3].get());
        Assert.assertEquals(1, ch.getCount(1).getHttpStatus()[4].get());
        Assert.assertEquals(1, ch.getCount(1).getHttpStatus()[5].get());
        Assert.assertEquals(1, ch.getCount(1).getHttpStatus()[0].get());
    }
}
