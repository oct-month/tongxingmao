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

import top.ablocker.tongxingmao.entity.Problem;

@Component
public class ProblemDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RowMapper<Problem> rowMapper = new BeanPropertyRowMapper<>(Problem.class);

    // 增加一个Problem，id的值注入problem中
    @Transactional
    public boolean addAnProblem(Problem problem)
    {
        try {
            String sql1 = "insert into `Problem`(title, content) values(?, ?)";
            jdbcTemplate.update(sql1, problem.getTitle(), problem.getContent());
            int problemId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
            problem.setId(problemId);

            String sql2 = "insert into `Problem-Option`(problemId, optionId) values(?, ?)";
            for (int optionId: problem.getOptionList())
            {
                jdbcTemplate.update(sql2, problemId, optionId);
            }

            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 删除一个Problem
    @Transactional
    public boolean deleteAnProblem(int id)
    {
        String sql = "delete from `Problem` where id=?";
        try {
            jdbcTemplate.update(sql, id);
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 更新一个Problem
    @Transactional
    public boolean modifyAnProblem(Problem problem)
    {
        String sql = "update `Problem` set title=?, content=? where id=?";
        try {
            jdbcTemplate.update(sql, problem.getTitle(), problem.getClass(), problem.getId());
            return true;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    // 获取一个Problem
    public Problem findAnProblem(int id)
    {
        String sql1 = "select * from `Problem` where id=?";
        String sql2 = "select optionId from `Problem-Option` where problemId=?";
        try {
            Problem problem = jdbcTemplate.queryForObject(sql1, rowMapper, id);
            List<Integer> options = jdbcTemplate.query(sql2, new IntegerRowMapper(), problem.getId());
            problem.setOptionList(options);
            return problem;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // 获取所有Problem
    public List<Problem> findAllProblem()
    {
        String sql1 = "select * from `Problem`";
        String sql2 = "select optionId from `Problem-Option` where problemId=?";
        try {
            List<Problem> problems = jdbcTemplate.query(sql1, rowMapper);
            for (Problem problem: problems)
            {
                List<Integer> options = jdbcTemplate.query(sql2, new IntegerRowMapper(), problem.getId());
                problem.setOptionList(options);
            }
            return problems;
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
