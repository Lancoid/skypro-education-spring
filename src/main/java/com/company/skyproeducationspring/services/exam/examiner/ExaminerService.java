package com.company.skyproeducationspring.services.exam.examiner;

import com.company.skyproeducationspring.models.Question;

import java.util.ArrayList;

public interface ExaminerService {

    ArrayList<Question> getQuestions(int amount);
}
