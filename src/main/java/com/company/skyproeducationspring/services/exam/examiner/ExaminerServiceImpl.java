package com.company.skyproeducationspring.services.exam.examiner;

import com.company.skyproeducationspring.models.Question;
import com.company.skyproeducationspring.services.exam.question.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        ArrayList<Question> result = new ArrayList<>();
        Question question;
        int counter = 0;

        while (result.size() < amount) {
            try {
                question = (counter % 2 != 0)
                        ? javaQuestionService.getRandomQuestion()
                        : mathQuestionService.getRandomQuestion();

                if (!result.contains(question)) {
                    result.add(question);
                    counter++;
                }
            } catch (Throwable throwable) {
                counter++;
            }
        }

        return result;
    }
}
