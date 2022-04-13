package com.company.skyproeducationspring.services.exam.question;

import com.company.skyproeducationspring.models.Question;

import java.util.ArrayList;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Question getRandomQuestion();

    ArrayList<Question> getAll();

    int getQuestionCount();
}
