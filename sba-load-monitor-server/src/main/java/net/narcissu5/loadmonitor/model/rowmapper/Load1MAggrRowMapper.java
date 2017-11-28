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
        return ret;
    }
}
