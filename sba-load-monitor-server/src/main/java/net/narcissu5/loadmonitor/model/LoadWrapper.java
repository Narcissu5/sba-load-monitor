package net.narcissu5.loadmonitor.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曾浩 on 2017/12/4.
 */
public class LoadWrapper {
    private Map<String,List<Load1MAggr>> loads;
    private HttpStatusModel httpStatus;

    public Map<String, List<Load1MAggr>> getLoads() {
        return loads;
    }

    public void setLoads(Map<String, List<Load1MAggr>> loads) {
        this.loads = loads;
    }

    public HttpStatusModel getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatusModel httpStatus) {
        this.httpStatus = httpStatus;
    }

    public static class HttpStatusModel {
        private int s1xx;
        private int s2xx;
        private int s3xx;
        private int s4xx;
        private int s5xx;

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
    }
}
