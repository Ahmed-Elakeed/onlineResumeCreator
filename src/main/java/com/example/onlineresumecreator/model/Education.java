package com.example.onlineresumecreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;
    private String educationDegree;
    private String educationPlace;
    private String startDate;
    private String endDate;


    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public Education() {
    }

    public Education(String educationDegree, String educationPlace, String startDate, String endDate) {
        this.educationDegree = educationDegree;
        this.educationPlace = educationPlace;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public String getEducationDegree() {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree) {
        this.educationDegree = educationDegree;
    }

    public String getEducationPlace() {
        return educationPlace;
    }

    public void setEducationPlace(String educationPlace) {
        this.educationPlace = educationPlace;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Education{" +
                "educationId=" + educationId +
                ", educationDegree='" + educationDegree + '\'' +
                ", educationPlace='" + educationPlace + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
