package com.company.skyproeducationspring.repositories.question;

import com.company.skyproeducationspring.models.Question;

import java.util.List;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    boolean contains(Question question);

    List<Question> getAll();

    int getQuestionCount();
}
