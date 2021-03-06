package net.narcissu5.loadmonitor.model;

import java.sql.Timestamp;

/**
 * Created by 曾浩 on 2017/11/20.
 */
public class Load1M {
    private long id;
    private String appName;
    private int port;
    private String hostName;
    private int count;
    private int minute;
    private int s1xx;
    private int s2xx;
    private int s3xx;
    private int s4xx;
    private int s5xx;
    private Timestamp timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getS1xx() {
        return s1xx;
    }

    public void setS1xx(int s1xx) {
        this.s1xx = s1xx;
    }

    public int getS2xx() {
        return s2xx;
    }

    public void setS2xx(int s2xx) {
        this.s2xx = s2xx;
    }

    public int getS3xx() {
        return s3xx;
    }

    public void setS3xx(int s3xx) {
        this.s3xx = s3xx;
    }

    public int getS4xx() {
        return s4xx;
    }

    public void setS4xx(int s4xx) {
        this.s4xx = s4xx;
    }

    public int getS5xx() {
        return s5xx;
    }

    public void setS5xx(int s5xx) {
        this.s5xx = s5xx;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
