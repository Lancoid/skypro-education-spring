package com.company.skyproeducationspring.services.exam.question;

import com.company.skyproeducationspring.models.Question;

import java.util.List;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Question getRandomQuestion();

    List<Question> getAll();

    int getQuestionCount();
}
