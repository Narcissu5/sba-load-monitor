package net.narcissu5.loadmonitor.model.rowmapper;

import net.narcissu5.loadmonitor.model.Load1M;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 曾浩 on 2017/11/20.
 */
public class Load1MRowMapper implements RowMapper<Load1M> {
    public static final Load1MRowMapper INSTANCE = new Load1MRowMapper();

    private Load1MRowMapper() {

    }

    @Override
    public Load1M mapRow(ResultSet rs, int rowNum) throws SQLException {
        Load1M load1M = new Load1M();
        load1M.setId(rs.getLong("id"));
        load1M.setAppName(rs.getString("app_name"));
        load1M.setPort(rs.getInt("port"));
        load1M.setHostName(rs.getString("host_name"));
        load1M.setCount(rs.getInt("count"));
        load1M.setMinute(rs.getInt("minute"));
        load1M.setS1xx(rs.getInt("s1xx"));
        load1M.setS2xx(rs.getInt("s2xx"));
        load1M.setS3xx(rs.getInt("s3xx"));
        load1M.setS4xx(rs.getInt("s4xx"));
        load1M.setS5xx(rs.getInt("s5xx"));
        return load1M;
    }
}
