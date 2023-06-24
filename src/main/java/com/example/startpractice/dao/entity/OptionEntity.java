package com.example.startpractice.dao.entity;

public class OptionEntity {
    private String optionContent;
    private String checked;
    private String questionId;

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "optionContent='" + optionContent + '\'' +
                ", checked='" + checked + '\'' +
                ", questionId='" + questionId + '\'' +
                '}';
    }
}