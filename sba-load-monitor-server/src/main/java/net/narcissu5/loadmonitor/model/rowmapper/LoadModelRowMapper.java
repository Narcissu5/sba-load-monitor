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
        return ret;
    }
}
