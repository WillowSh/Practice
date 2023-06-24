package com.example.startpractice.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class AnswerSheetEntity implements Serializable {
    private String id;
    private String respondent;
    private String qNRId;
    private Date answerDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRespondent() {
        return respondent;
    }

    public void setRespondent(String respondent) {
        this.respondent = respondent;
    }

    public String getqNRId() {
        return qNRId;
    }

    public void setqNRId(String qNRId) {
        this.qNRId = qNRId;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "AnswerSheetEntity{" +
                "id='" + id + '\'' +
                ", respondent='" + respondent + '\'' +
                ", qNRId='" + qNRId + '\'' +
                ", answerDate=" + answerDate +
                '}';
    }
}
