package net.narcissu5.loadmonitor.model.rowmapper;

import net.narcissu5.loadmonitor.model.Load1MAggr;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Load1MAggrRowMapper implements RowMapper<Load1MAggr> {
    public static final Load1MAggrRowMapper INSTANCE = new Load1MAggrRowMapper();

    private Load1MAggrRowMapper() {
    }

    @Override
    public Load1MAggr mapRow(ResultSet rs, int rowNum) throws SQLException {
        Load1MAggr ret = new Load1MAggr();
        ret.setId(rs.getLong("id"));
        ret.setAppName(rs.getString("app_name"));
        ret.setCount(rs.getInt("count"));
        ret.setMinute(rs.getInt("minute"));
        ret.setCreatedAt(rs.getTimestamp("created_at"));
        ret.setS1xx(rs.getInt("s1xx"));
        ret.setS2xx(rs.getInt("s2xx"));
        ret.setS3xx(rs.getInt("s3xx"));
        ret.setS4xx(rs.getInt("s4xx"));
        ret.setS5xx(rs.getInt("s5xx"));
        return ret;
    }
}
