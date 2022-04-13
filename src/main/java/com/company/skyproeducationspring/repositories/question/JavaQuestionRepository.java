package com.company.skyproeducationspring.repositories.question;

import com.company.skyproeducationspring.models.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class JavaQuestionRepository implements QuestionRepository {
    private final Map<Integer, Question> questionMap;

    public JavaQuestionRepository() {
        this.questionMap = new HashMap<>();
    }

    @Override
    public Question add(Question question) {
        questionMap.put(question.hashCode(), question);

        return questionMap.get(question.hashCode());
    }

    @Override
    public Question remove(Question question) {
        return questionMap.remove(question.hashCode());
    }

    @Override
    public boolean contains(Question question) {
        return questionMap.containsKey(question.hashCode()) && questionMap.get(question.hashCode()).equals(question);
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
