package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.services.exam.question.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "")
    public String getQuestions(Model model) {
        model.addAttribute("type", "Java");
        model.addAttribute("questions", questionService.getAll());

        return "exam/all";
    }

    @GetMapping(path = "/add")
    public String addQuestion(String question, String answer, Model model) {
        model.addAttribute("type", "Java");
        model.addAttribute("question", questionService.add(question, answer));

        return "exam/add";
    }

    @GetMapping(path = "/remove")
    public String removeQuestion(String question, String answer, Model model) {
        model.addAttribute("type", "Java");
        model.addAttribute("question", questionService.remove(new Question(question, answer)));

        return "exam/remove";
    }
}
