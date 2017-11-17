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
        ch.incr(1);
        ch.incr(1);
        Assert.assertEquals(2, ch.getCount(1).getCount());
    }

    @Test
    public void refreshTest() {
        CountHolder ch = new CountHolder();
        for (int idx = 0; idx < 300; idx++) {
            for (int idx2 = 0; idx2 < idx; idx2++) {
                ch.incr(idx);
            }
        }

        Assert.assertEquals(255, ch.getCount(255).getCount());
        Assert.assertEquals(290, ch.getCount(290).getCount());
        Assert.assertEquals(299, ch.getCount(299).getCount());
    }
}
