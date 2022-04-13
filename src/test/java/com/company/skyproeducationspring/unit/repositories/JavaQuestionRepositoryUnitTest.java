package com.company.skyproeducationspring.unit.repositories;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.repositories.question.JavaQuestionRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Java Question Repository Test")
@Epic("JavaQuestionRepository")
public class JavaQuestionRepositoryUnitTest {
    private JavaQuestionRepository questionRepository;

    @BeforeEach
    public void init() {
        questionRepository = new JavaQuestionRepository();
    }


    @Description("Check repository correct all methods processing")
    @Test
    public void testAllMethods() {
        List<Question> questionList = getQuestionList();

        int counter = 0;
        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
            assertTrue(questionRepository.contains(question));
            assertEquals(++counter, questionRepository.getQuestionCount());
        }

        List<Question> resultQuestionList = questionRepository.getAll();

        Collections.sort(questionList);
        Collections.sort(resultQuestionList);

        assertEquals(questionList, resultQuestionList);

        for (Question question : questionList) {
            assertEquals(counter, questionRepository.getQuestionCount());
            assertEquals(question, questionRepository.remove(question));
            assertEquals(--counter, questionRepository.getQuestionCount());
            assertFalse(questionRepository.contains(question));
        }
    }

    private List<Question> getQuestionList() {
        List<Question> result = new ArrayList<>();
        Lorem lorem = (new Faker()).lorem();

        for (int counter = 0; counter < 5; counter++) {
            result.add(new Question(lorem.sentence(5), lorem.sentence(5)));
        }

        return result;
    }
}
