package net.narcissu5.loadmonitor.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by 曾浩 on 2017/11/17.
 */
public class LoadModel {
    @JsonIgnore
    private String appName;
    private long minute;
    private int count;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LoadModel{" +
                "minute=" + minute +
                ", count=" + count +
                '}';
    }

    public void add(LoadModel model) {
        if (model != null) {
            this.count += model.count;
        }
    }
}
