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

import top.ablocker.tongxingmao.entity.Option;

@Component
public class OptionDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RowMapper<Option> rowMapper = new BeanPropertyRowMapper<>(Option.class);

    // 增加一个Option，id的值注入option中
    @Transactional
    public boolean addAnOption(Option option)
    {
        try {
            String sql1 = "insert into `Option`(content) values(?)";
            jdbcTemplate.update(sql1, option.getContent());
            int optionId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
            option.setId(optionId);

            String sql2 = "insert into `Option-TAG`(optionId, TAGName) values(?, ?)";
            for (String tagName: option.getTAGList())
            {
                jdbcTemplate.update(sql2, optionId, tagName);
            }

            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 删除一个Option
    @Transactional
    public boolean deleteAnOption(int id)
    {
        String sql = "delete from `Option` where id=?";
        try {
            jdbcTemplate.update(sql, id);
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 修改一个Option的content
    @Transactional
    public boolean modifyAnOption(Option option)
    {
        String sql = "update `Option` set content=?";
        try {
            jdbcTemplate.update(sql, option.getContent());
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 获取一个Option
    public Option findAnOption(int id)
    {
        String sql1 = "select * from `Option` where id=?";
        String sql2 = "select TAGName from `Option-TAG` where optionId=?";
        try {
            Option option = jdbcTemplate.queryForObject(sql1, rowMapper, id);
            List<String> tagNames = jdbcTemplate.query(sql2, new StringRowMapper(), option.getId());
            option.setTAGList(tagNames);
            return option;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // 获取所有Option
    public List<Option> findAllOption()
    {
        String sql1 = "select * from `Option`";
        String sql2 = "select TAGName from `Option-TAG` where optionId=?";
        try {
            List<Option> optionList = jdbcTemplate.query(sql1, rowMapper);
            for (Option option: optionList)
            {
                List<String> tagNames = jdbcTemplate.query(sql2, new StringRowMapper(), option.getId());
                option.setTAGList(tagNames);
            }
            return optionList;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
