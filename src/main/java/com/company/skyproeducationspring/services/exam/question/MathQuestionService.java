package com.company.skyproeducationspring.services.exam.question;

import com.company.skyproeducationspring.models.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    @Override
    public Question add(String question, String answer) {
        throw new RuntimeException("Метод не поддерживается.");
    }

    @Override
    public Question add(Question question) {
        throw new RuntimeException("Метод не поддерживается.");
    }

    @Override
    public Question remove(Question question) {
        throw new RuntimeException("Метод не поддерживается.");
    }

    @Override
    public Question getRandomQuestion() {
        Random randomGenerator = new Random();

        int a = randomGenerator.nextInt(500);
        int b = randomGenerator.nextInt(500);

        switch (randomGenerator.nextInt(4)) {
            case 0:
                return new Question(a + " + " + b, String.valueOf(a + b));
            case 1:
                return new Question(a + " - " + b, String.valueOf(a - b));
            case 2:
                return new Question(a + " * " + b, String.valueOf(a * b));
            case 3:
                return new Question(a + " / " + b, String.valueOf((float) a / (float) b));
            default:
                throw new RuntimeException("Ошибка рандомизатора");
        }
    }

    @Override
    public ArrayList<Question> getAll() {
        throw new RuntimeException("Метод не поддерживается.");
    }

    @Override
    public int getQuestionCount() {
        throw new RuntimeException("Метод не поддерживается.");
    }
}
