package com.example.startpractice.dao.entity;

import java.io.Serializable;
import java.util.List;

public class QuestionEntity implements Serializable {
    private String id;
    private String questionType;
    private String questionContent;
    private String qNRId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getqNRId() {
        return qNRId;
    }

    public void setqNRId(String qNRId) {
        this.qNRId = qNRId;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id='" + id + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", qNRId='" + qNRId + '\'' +
                '}';
    }
}