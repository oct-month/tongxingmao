package top.ablocker.tongxingmao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.ablocker.tongxingmao.dao.TAGDAO;
import top.ablocker.tongxingmao.entity.TAG;

@RestController
@RequestMapping("/api/tagapi")
public class TAGController
{
    @Autowired
    private TAGDAO tagDAO;

    @GetMapping("/tags")
    public List<TAG> getAllTags()
    {
        return tagDAO.findAllTAG();
    }

    @GetMapping("/tag/{tagName}")
    public TAG getAnTag(@PathVariable("tagName") String tagName)
    {
        return tagDAO.findAnTAG(tagName);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public TAG addAnTag(@RequestBody TAG tag)
    {
        if (tagDAO.addAnTAG(tag))
            return tag;
        else
            return null;
    }
}
