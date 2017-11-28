package net.narcissu5.loadmonitor.model;

import java.sql.Timestamp;

/**
 * Created by 曾浩 on 2017/11/28.
 */
public class Load1MAggr {
    private long id;
    private String appName;
    private int count;
    private int minute;
    private Timestamp createdAt;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
