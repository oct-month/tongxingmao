package top.ablocker.tongxingmao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import top.ablocker.tongxingmao.dao.OptionDAO;
import top.ablocker.tongxingmao.dao.ProblemDAO;
import top.ablocker.tongxingmao.dao.TAGDAO;
import top.ablocker.tongxingmao.entity.Option;
import top.ablocker.tongxingmao.entity.Problem;
import top.ablocker.tongxingmao.entity.TAG;

@SpringBootTest
public class TongxingmaoDAOTests
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TAGDAO tagDAO;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private ProblemDAO problemDAO;

    @Test
    void tagDAOTest()
    {
        TAG tag = new TAG("爱学习的好学生", "很爱好学习，没有学习就活不下去了。");
        assertTrue(tagDAO.addAnTAG(tag));
        assertFalse(tagDAO.addAnTAG(tag));
        assertTrue(tagDAO.deleteAnTAG(tag.getName()));
    }

    @Test
    void optionDAOTest()
    {
        TAG tag1 = new TAG("书呆子", "很呆很呆，呆得不得了");
        TAG tag2 = new TAG("宅", "不出门");
        TAG tag3 = new TAG("闷", "不说话");
        TAG[] tags = {tag1, tag2, tag3};

        assertTrue(tagDAO.addAnTAG(tag1));
        assertTrue(tagDAO.addAnTAG(tag2));
        assertTrue(tagDAO.addAnTAG(tag3));
        
        List<String> tagList = Arrays.asList(tag1.getName(), tag2.getName(), tag3.getName());
        Option option = new Option("这不就是个书呆子吗", tagList);

        assertTrue(optionDAO.addAnOption(option));

        option = optionDAO.findAnOption(option.getId());

        assertNotNull(option);
        assertEquals(option.getContent(), "这不就是个书呆子吗");
        assertTrue(option.getId() > 0);

        for (String tagName: option.getTAGList())
        {
            assertEquals(tags[option.getTAGList().indexOf(tagName)].getName(), tagName);
        }

        List<Option> options = optionDAO.findAllOption();

        for (Option option2: options)
        {
            assertNotNull(option2);
            assertTrue(option2.getId() > 0);
            assertTrue(option2.getTAGList().size() > 0);
        }

        assertTrue(optionDAO.deleteAnOption(option.getId()));
        
        for (TAG tag: tags)
        {
            assertTrue(tagDAO.deleteAnTAG(tag.getName()));
        }
    }

    @Test
    void problemDAOTest()
    {
        TAG tag1 = new TAG("书呆子2", "很呆很呆，呆得不得了");
        TAG tag2 = new TAG("宅2", "不出门");
        TAG tag3 = new TAG("闷2", "不说话");

        assertTrue(tagDAO.addAnTAG(tag1));
        assertTrue(tagDAO.addAnTAG(tag2));
        assertTrue(tagDAO.addAnTAG(tag3));
        
        List<String> tagList = Arrays.asList(tag1.getName(), tag2.getName(), tag3.getName());
        Option option = new Option("这不就是个书呆子吗2", tagList);

        assertTrue(optionDAO.addAnOption(option));

        Problem problem = new Problem("人品问题", "你觉得他怎么样", Arrays.asList(option.getId()));
        
        assertTrue(problemDAO.addAnProblem(problem));
        assertTrue(problem.getId() > 0);
        assertEquals(problem.getOptionList().size(), 1);
    }
}
