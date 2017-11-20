package net.narcissu5.loadmonitor.dao;

import net.narcissu5.loadmonitor.model.Load1M;
import net.narcissu5.loadmonitor.model.rowmapper.Load1MRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by 曾浩 on 2017/11/20.
 */
@Repository
public class SbaLoad1MDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(String appName, String hostName, int port, int count, int minute) {
        jdbcTemplate.update("INSERT INTO load_1m " +
                "(app_name,port,host_name,count,minute) " +
                "VALUES (?,?,?,?,?)", appName, port, hostName, count, minute);
    }

    public List<Load1M> findByMinuteAndHostName(int minute, String hostName) {
        return jdbcTemplate.query("SELECT id,app_name,port,host_name," +
                "count,minute,created_at " +
                "FROM load_1m WHERE host_name=? AND minute=?", Load1MRowMapper.INSTANCE, hostName, minute);
    }
}
