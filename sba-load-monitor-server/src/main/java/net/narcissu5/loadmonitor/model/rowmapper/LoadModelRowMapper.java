package net.narcissu5.loadmonitor.model.rowmapper;

import net.narcissu5.loadmonitor.util.LoadModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 曾浩 on 2017/11/27.
 */
public class LoadModelRowMapper implements RowMapper<LoadModel> {
    public static final LoadModelRowMapper INSTANCE = new LoadModelRowMapper();

    private LoadModelRowMapper() {

    }

    @Override
    public LoadModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoadModel ret = new LoadModel();
        ret.setMinute(rs.getInt("minute"));
        ret.setCount(rs.getInt("count"));
        ret.setAppName(rs.getString("app_name"));
        int httpStatus[] = new int[]{0, rs.getInt("s1xx"), rs.getInt("s2xx"), rs.getInt("s3xx"),
                rs.getInt("s4xx"), rs.getInt("s5xx")};
        httpStatus[0] = ret.getCount() - httpStatus[1] - httpStatus[2] - httpStatus[3] - httpStatus[4] - httpStatus[5];
        return ret;
    }
}
