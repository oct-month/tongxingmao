package top.ablocker.tongxingmao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

class StringRowMapper implements RowMapper<String>
{
    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return rs.getString(1);
    }
}
