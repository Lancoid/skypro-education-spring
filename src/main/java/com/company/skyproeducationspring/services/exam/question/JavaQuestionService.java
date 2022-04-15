package com.company.skyproeducationspring.services.exam.question;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.repositories.question.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (questionRepository.contains(question)) {
            throw new RuntimeException("Такой вопрос уже добавлен.");
        }

        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        if (questionRepository.contains(question)) {
            return questionRepository.remove(question);
        }

        throw new RuntimeException("Такой вопрос не добавлен.");
    }

    @Override
    public Question getRandomQuestion() {
        if (getQuestionCount() > 0) {
            int randomIndex = (new Random()).nextInt(getQuestionCount());

            return questionRepository.getAll().get(randomIndex);
        }

        throw new RuntimeException("Нет добавленных вопросов.");
    }

    @Override
    public List<Question> getAll() {
        if (getQuestionCount() > 0) {
            return questionRepository.getAll();
        }

        throw new RuntimeException("Нет добавленных вопросов.");
    }

    @Override
    public int getQuestionCount() {
        return questionRepository.getQuestionCount();
    }
}
