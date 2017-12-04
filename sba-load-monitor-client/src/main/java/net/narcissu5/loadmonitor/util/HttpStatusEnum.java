package net.narcissu5.loadmonitor.util;

/**
 * Created by 曾浩 on 2017/12/4.
 */
public enum HttpStatusEnum {
    S100(1), S200(2), S300(3), S400(4), S500(5), UNKNOWN(0);

    public final int index;

    HttpStatusEnum(int index) {
        this.index = index;
    }

    public static HttpStatusEnum fromStatusCode(int httpStatusCode) {
        if (httpStatusCode < 100 || httpStatusCode >= 600) {
            return UNKNOWN;
        } else if (httpStatusCode < 200) {
            return S100;
        } else if (httpStatusCode < 300) {
            return S200;
        } else if (httpStatusCode < 400) {
            return S300;
        } else if (httpStatusCode < 500) {
            return S400;
        } else if (httpStatusCode < 600) {
            return S500;
        } else {
            return UNKNOWN;
        }
    }
}
