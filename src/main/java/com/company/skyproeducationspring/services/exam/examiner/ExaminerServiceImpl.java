package com.company.skyproeducationspring.services.exam.examiner;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.services.exam.question.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Qualifier("javaQuestionService")
    private final QuestionService javaQuestionService;

    @Qualifier("mathQuestionService")
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public ArrayList<Question> getQuestions(int amount) {
        Map<Integer, Question> questionMap = new HashMap<>();
        Question question;
        int key;
        int counter = 0;

        while (questionMap.size() < amount) {
            try {
                question = counter % 2 == 0
                        ? javaQuestionService.getRandomQuestion()
                        : mathQuestionService.getRandomQuestion();

                key = question.hashCode();

                if (!questionMap.containsKey(key)) {
                    questionMap.put(key, question);
                }
            } catch (Throwable ignored) {
            } finally {
                counter++;
            }

        }

        return new ArrayList<>(questionMap.values());
    }
}
