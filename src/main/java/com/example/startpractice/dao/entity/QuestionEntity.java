package com.example.startpractice.dao.entity;

import java.util.List;

public class QuestionEntity {
    private String id;
    private String questionType;
    private String questionContent;
    private List<OptionEntity> options;
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

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }

    public String getqNRId() {
        return qNRId;
    }

    public void setqNRId(String qNRId) {
        this.qNRId = qNRId;
    }
}
