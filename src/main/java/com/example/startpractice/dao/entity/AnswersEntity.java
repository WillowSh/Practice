package com.example.startpractice.dao.entity;

public class AnswersEntity {
    private String questionId;
    private String answer;
    private String asId;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAsId() {
        return asId;
    }

    public void setAsId(String asId) {
        this.asId = asId;
    }

    @Override
    public String toString() {
        return "AnswersEntity{" +
                "questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", asId='" + asId + '\'' +
                '}';
    }
}
