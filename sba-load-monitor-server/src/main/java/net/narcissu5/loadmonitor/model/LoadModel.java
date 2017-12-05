package net.narcissu5.loadmonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 曾浩 on 2017/11/17.
 */
public class LoadModel {
    public static final Logger logger = LoggerFactory.getLogger(LoadModel.class);

    @JsonIgnore
    private String appName;
    private long minute;
    private int count;
    private int[] httpStatus;

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

    public int[] getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int[] httpStatus) {
        this.httpStatus = httpStatus;
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
            if (this.httpStatus != null && this.httpStatus.length == 6 &&
                    model.httpStatus != null && model.httpStatus.length == 6) {
                this.httpStatus[0] += model.httpStatus[0];
                this.httpStatus[1] += model.httpStatus[1];
                this.httpStatus[2] += model.httpStatus[2];
                this.httpStatus[3] += model.httpStatus[3];
                this.httpStatus[4] += model.httpStatus[4];
                this.httpStatus[5] += model.httpStatus[5];
            } else {
                logger.warn("Unexpected httpStatus length:{} or {}", this.httpStatus.length, model.httpStatus.length);
            }
        }
    }
}
