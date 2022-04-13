package com.company.skyproeducationspring.unit.services;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.repositories.question.QuestionRepository;
import com.company.skyproeducationspring.services.exam.question.JavaQuestionService;
import com.github.javafaker.Faker;
import com.github.javafaker.Lorem;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("Java Question Service Test")
@Epic("JavaQuestionService")
@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceUnitTest {
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Mock
    private QuestionRepository questionRepositoryMock;

    @Description("Check service correct `add` processing")
    @Test
    public void testAdd() {
        List<Question> questionList = getQuestionList();

        for (Question question : questionList) {
            when(questionRepositoryMock.add(question)).thenReturn(question);
            assertEquals(question, javaQuestionService.add(question.getQuestion(), question.getAnswer()));
        }
    }

    @Description("Check service correct `getQuestionCount` processing")
    @Test
    public void testGetQuestionCount() {
        for (int counter = 0; counter < 5; counter++) {
            when(questionRepositoryMock.getQuestionCount()).thenReturn(++counter);
            assertEquals(counter, javaQuestionService.getQuestionCount());
        }
    }

    @Description("Check service correct `contains` and `remove` processing")
    @Test
    public void testContainsAndRemove() {
        List<Question> questionList = getQuestionList();

        for (Question question : questionList) {
            when(questionRepositoryMock.contains(question)).thenReturn(true);
            when(questionRepositoryMock.remove(question)).thenReturn(question);
            assertEquals(question, javaQuestionService.remove(question));
        }
    }

    @Description("Check service correct `getRandomQuestion` processing")
    @Test
    public void testGetRandomQuestion() {
        List<Question> questionList = getQuestionList();
        Question question;

        when(questionRepositoryMock.getQuestionCount()).thenReturn(questionList.size());
        when(questionRepositoryMock.getAll()).thenReturn((ArrayList<Question>) questionList);

        for (int i = 0; i < 10; i++) {
            question = javaQuestionService.getRandomQuestion();

            assertTrue(questionList.contains(question));
        }
    }

    @Description("Check service correct `getAll` processing")
    @Test
    public void testGetAll() {
        List<Question> questionList = getQuestionList();

        when(questionRepositoryMock.getQuestionCount()).thenReturn(questionList.size());
        when(questionRepositoryMock.getAll()).thenReturn((ArrayList<Question>) questionList);
        assertEquals(questionList, javaQuestionService.getAll());
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
