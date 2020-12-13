package top.ablocker.tongxingmao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.ablocker.tongxingmao.dao.OptionDAO;
import top.ablocker.tongxingmao.entity.Option;

@RestController
@RequestMapping("/api/optionapi")
public class OptionController
{
    @Autowired
    private OptionDAO optionDAO;

    @GetMapping("/options")
    public List<Option> getAllOptions()
    {
        return optionDAO.findAllOption();
    }

    @GetMapping("/option/{optionId}")
    public Option getAnOption(@PathVariable("optionId") int id)
    {
        return optionDAO.findAnOption(id);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public Option addAnOption(@RequestBody Option option)
    {
        if (optionDAO.addAnOption(option))
            return option;
        else
            return null;
    }
}
