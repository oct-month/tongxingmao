package top.ablocker.tongxingmao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

class IntegerRowMapper implements RowMapper<Integer>
{
    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return rs.getInt(1);
    }
}
