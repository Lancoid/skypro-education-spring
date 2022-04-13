package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.services.exam.examiner.ExaminerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/get")
    public String getQuestions(int amount, Model model) {
        model.addAttribute("type", "Java");
        model.addAttribute("questions", examinerService.getQuestions(amount));

        return "exam/all";
    }
}
