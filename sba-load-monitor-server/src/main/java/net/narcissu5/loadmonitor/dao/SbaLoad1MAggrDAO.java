package net.narcissu5.loadmonitor.dao;

import net.narcissu5.loadmonitor.model.Load1MAggr;
import net.narcissu5.loadmonitor.model.rowmapper.Load1MAggrRowMapper;
import net.narcissu5.loadmonitor.model.rowmapper.LoadModelRowMapper;
import net.narcissu5.loadmonitor.util.LoadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by 曾浩 on 2017/11/28.
 */
@Repository
public class SbaLoad1MAggrDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(String appName, int count, int minute) {
        jdbcTemplate.update("INSERT INTO load_1m_aggr (app_name,count,minute) VALUES (?,?,?)",
                appName, count, minute);
    }

    /**
     * for test only
     *
     * @param appName
     */
    public List<Load1MAggr> findByAppName(String appName) {
        return jdbcTemplate.query("SELECT id,app_name,count,minute,created_at " +
                "FROM load_1m_aggr WHERE app_name = ?", Load1MAggrRowMapper.INSTANCE, appName);
    }

    public List<LoadModel> sinceThen(int minute) {
        return jdbcTemplate.query("SELECT count,minute,app_name " +
                "FROM load_1m WHERE minute > ? " +
                "ORDER BY app_name,minute", LoadModelRowMapper.INSTANCE, minute);
    }
}