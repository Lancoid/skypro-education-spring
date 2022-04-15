package com.company.skyproeducationspring.repositories.question;

import com.company.skyproeducationspring.exceptions.question.QuestionNotAddedException;
import com.company.skyproeducationspring.exceptions.question.QuestionNotRemoveException;
import com.company.skyproeducationspring.models.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questionSet;

    public JavaQuestionRepository() {
        this.questionSet = new HashSet<>();
    }

    @Override
    public Question add(Question question) {
        if (questionSet.add(question)) {
            return question;
        }

        throw new QuestionNotAddedException();
    }

    @Override
    public Question remove(Question question) {
        if (questionSet.remove(question)) {
            return question;
        }

        throw new QuestionNotRemoveException();
    }

    @Override
    public boolean contains(Question question) {
        return questionSet.contains(question);
    }

    @Override
    public List<Question> getAll() {
        return new ArrayList<>(questionSet);
    }

    @Override
    public int getQuestionCount() {
        return questionSet.size();
    }
}
