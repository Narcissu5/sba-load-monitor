package net.narcissu5.loadmonitor.service;

import net.narcissu5.loadmonitor.dao.SbaLoad1MAggrDAO;
import net.narcissu5.loadmonitor.dao.SbaLoad1MDAO;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<String, List<LoadModel>> getToday() {
        int minute = (int) (System.currentTimeMillis() / 60_000);
        minute = minute / 1440 * 1440;
        List<LoadModel> minutes = sbaLoad1MAggrDAO.sinceThen(minute);
        Map<String, List<LoadModel>> maps = minutes.stream().collect(Collectors.groupingBy(LoadModel::getAppName));
        return maps;
    }
}