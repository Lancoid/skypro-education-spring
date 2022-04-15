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
    private List<Question> questionList;

    @BeforeEach
    public void init() {
        questionRepository = new JavaQuestionRepository();
        questionList = new ArrayList<>();

        Lorem lorem = (new Faker()).lorem();
        for (int counter = 0; counter < 5; counter++) {
            questionList.add(new Question(lorem.sentence(5), lorem.sentence(5)));
        }
    }

    @Description("Check repository correct `add` methods processing")
    @Test
    public void testAdd() {
        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
        }
    }

    @Description("Check repository correct `contains` methods processing")
    @Test
    public void testContains() {
        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
            assertTrue(questionRepository.contains(question));
        }
    }

    @Description("Check repository correct `getQuestionCount` methods processing")
    @Test
    public void testGetQuestionCount() {
        int counter = 0;

        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
            assertEquals(++counter, questionRepository.getQuestionCount());
        }
    }

    @Description("Check repository correct `contains` methods processing")
    @Test
    public void testRemove() {
        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
        }

        int counter = 5;
        for (Question question : questionList) {
            assertTrue(questionRepository.contains(question));

            assertEquals(counter, questionRepository.getQuestionCount());
            assertEquals(question, questionRepository.remove(question));
            assertEquals(--counter, questionRepository.getQuestionCount());

            assertFalse(questionRepository.contains(question));
        }
    }

    @Description("Check repository correct `getAll` methods processing")
    @Test
    public void testGetAll() {
        for (Question question : questionList) {
            assertEquals(question, questionRepository.add(question));
        }

        List<Question> resultQuestionList = questionRepository.getAll();

        Collections.sort(questionList);
        Collections.sort(resultQuestionList);

        assertEquals(questionList, resultQuestionList);
    }
}
