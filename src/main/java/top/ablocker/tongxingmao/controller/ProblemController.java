package top.ablocker.tongxingmao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.ablocker.tongxingmao.dao.ProblemDAO;
import top.ablocker.tongxingmao.entity.Problem;

@RestController
@RequestMapping("/api/problemapi")
public class ProblemController
{
    @Autowired
    private ProblemDAO problemDAO;

    @GetMapping("/problems")
    public List<Problem> getAllProblems()
    {
        return problemDAO.findAllProblem();
    }

    @GetMapping("/problem/{problemId}")
    public Problem getAnProblem(@PathVariable("problemId") int id)
    {
        return problemDAO.findAnProblem(id);
    }

    @PostMapping(value = "/add", produces = "application/json")
    public Problem addAnProblem(@RequestBody Problem problem)
    {
        if (problemDAO.addAnProblem(problem))
            return problem;
        else
            return null;
    }
}
