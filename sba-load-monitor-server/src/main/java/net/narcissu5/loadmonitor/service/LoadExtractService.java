package net.narcissu5.loadmonitor.service;

import net.narcissu5.loadmonitor.dao.SbaLoad1MAggrDAO;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import net.narcissu5.loadmonitor.model.Load1MAggr;
import net.narcissu5.loadmonitor.model.LoadModel;
import net.narcissu5.loadmonitor.model.LoadWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 曾浩 on 2017/11/21.
 */
@Service
public class LoadExtractService {
    @Autowired
    SbaLoad1MDAO sbaLoad1MDAO;

    @Autowired
    SbaLoad1MAggrDAO sbaLoad1MAggrDAO;

    ZoneId zoneId = ZoneId.systemDefault();

    public LoadWrapper getToday() {
        LoadWrapper ret = new LoadWrapper();
        int minute = (int) (LocalDate.now().atStartOfDay(zoneId).toEpochSecond() / 60);
        List<Load1MAggr> minutes = sbaLoad1MAggrDAO.sinceThen(minute);
        Map<String, List<Load1MAggr>> maps = minutes.stream().collect(Collectors.groupingBy(Load1MAggr::getAppName));
        ret.setLoads(maps);
        LoadWrapper.HttpStatusModel statusCode = new LoadWrapper.HttpStatusModel();
        ret.setHttpStatus(statusCode);
        for (Load1MAggr lm : minutes) {
            statusCode.setS1xx(statusCode.getS1xx() + lm.getS1xx());
            statusCode.setS2xx(statusCode.getS2xx() + lm.getS2xx());
            statusCode.setS3xx(statusCode.getS3xx() + lm.getS3xx());
            statusCode.setS4xx(statusCode.getS4xx() + lm.getS4xx());
            statusCode.setS5xx(statusCode.getS5xx() + lm.getS5xx());
        }

        return ret;
    }
}
