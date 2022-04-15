package com.company.skyproeducationspring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class Question implements Comparable<Question> {
    private final String question;
    private final String answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Question)) {
            return false;
        }

        Question anotherQuestion = (Question) o;

        return question.equals(anotherQuestion.getQuestion()) && answer.equals(anotherQuestion.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return "Question {" + "question='" + question + "'" + ", answer='" + answer + "}";
    }

    @Override
    public int compareTo(Question o) {
        return (this.question).compareTo(o.getQuestion());
    }
}
