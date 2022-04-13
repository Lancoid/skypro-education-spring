package com.company.skyproeducationspring.controllers;

import com.company.skyproeducationspring.exceptions.MethodNotAllowedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam/math")
public class MathQuestionController {
    @GetMapping(path = "")
    public String getQuestions() {
        throw new MethodNotAllowedException();
    }

    @GetMapping(path = "/add")
    public String addQuestion() {
        throw new MethodNotAllowedException();
    }

    @GetMapping(path = "/remove")
    public String removeQuestion() {
        throw new MethodNotAllowedException();
    }
}
