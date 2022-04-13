package com.company.skyproeducationspring.repositories.question;

import com.company.skyproeducationspring.models.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JavaQuestionRepository implements QuestionRepository {
    private final Map<Integer, Question> questionMap;

    public JavaQuestionRepository() {
        this.questionMap = new HashMap<>();
    }

    @Override
    public Question add(Question question) {
        int index = Objects.hash(question.getQuestion());

        questionMap.put(index, question);

        return questionMap.get(index);
    }

    @Override
    public Question remove(Question question) {
        int index = Objects.hash(question.getQuestion());

        return questionMap.remove(index);
    }

    @Override
    public boolean contains(Question question) {
        int index = Objects.hash(question.getQuestion());

        return questionMap.containsKey(index);
    }

    @Override
    public ArrayList<Question> getAll() {
        return new ArrayList<>(questionMap.values());
    }

    @Override
    public int getQuestionCount() {
        return questionMap.size();
    }
}
