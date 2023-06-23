package com.example.startpractice.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class QNREntity implements Serializable {
    private String id;
    private String qNRType;
    private String qNRName;
    private String qNRContent;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private String projectId;
    private String createdBy;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getqNRType() {
        return qNRType;
    }

    public void setqNRType(String qNRType) {
        this.qNRType = qNRType;
    }

    public String getqNRName() {
        return qNRName;
    }

    public void setqNRName(String qNRName) {
        this.qNRName = qNRName;
    }

    public String getqNRContent() {
        return qNRContent;
    }

    public void setqNRContent(String qNRContent) {
        this.qNRContent = qNRContent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QNREntity{" +
                "id='" + id + '\'' +
                ", qNRType='" + qNRType + '\'' +
                ", qNRName='" + qNRName + '\'' +
                ", qNRContent='" + qNRContent + '\'' +
                ", creationDate=" + creationDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectId='" + projectId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
