package com.company.skyproeducationspring.unit.services;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.services.exam.examiner.ExaminerServiceImpl;
import com.company.skyproeducationspring.services.exam.question.JavaQuestionService;
import com.company.skyproeducationspring.services.exam.question.MathQuestionService;
import com.github.javafaker.Faker;
import com.github.javafaker.FunnyName;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Examiner Service Test")
@Epic("ExaminerService")
@ExtendWith(MockitoExtension.class)
public class ExaminerServiceUnitTest {
    private ExaminerServiceImpl examinerService;

    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;

    @BeforeEach
    public void init() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Description("Check service correct `getQuestions` processing")
    @Test
    public void testGetQuestions() {
        List<Question> expectedQuestionList = new ArrayList<>();
        FunnyName funnyName = (new Faker()).funnyName();

        Question javaQuestion = new Question(funnyName.name(), funnyName.name());
        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestion);
        expectedQuestionList.add(javaQuestion);

        Question mathQuestion = new Question(funnyName.name(), funnyName.name());
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion);
        expectedQuestionList.add(mathQuestion);

        List<Question> resultQuestionList = examinerService.getQuestions(2);

        Collections.sort(expectedQuestionList);
        Collections.sort(resultQuestionList);

        assertEquals(expectedQuestionList, resultQuestionList);
    }
}
