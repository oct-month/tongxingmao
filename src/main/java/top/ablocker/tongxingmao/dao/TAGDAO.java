package top.ablocker.tongxingmao.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import top.ablocker.tongxingmao.entity.TAG;

@Component
public class TAGDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RowMapper<TAG> rowMapper = new BeanPropertyRowMapper<>(TAG.class);

    // 增加一个TAG
    @Transactional
    public boolean addAnTAG(TAG tag)
    {
        String sql = "insert into `TAG`(name, describ) values(?, ?)";
        try {
            jdbcTemplate.update(sql, tag.getName(), tag.getDescrib());
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 删除一个TAG
    @Transactional
    public boolean deleteAnTAG(String name)
    {
        String sql = "delete from `TAG` where name=?";
        try {
            jdbcTemplate.update(sql, name);
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 修改一个TAG
    @Transactional
    public boolean modifyAnTAG(TAG tag)
    {
        String sql = "update `TAG` set describ=? where name=?";
        try {
            jdbcTemplate.update(sql, tag.getDescrib(), tag.getName());
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 获取一个TAG
    public TAG getAnTAG(String name)
    {
        String sql = "select * from `TAG` where name=?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, name);
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // 获取所有TAG
    public List<TAG> getAllTAG()
    {
        String sql = "select * from `TAG`";
        try {
            return jdbcTemplate.query(sql, rowMapper);
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
