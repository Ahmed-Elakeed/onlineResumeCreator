package com.example.onlineresumecreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long experienceId;
    private String experiencePosition;
    private String experiencePlace;
    private String startDate;
    private String endDate;


    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public Experience() {
    }

    public Experience(String experiencePosition, String experiencePlace, String startDate, String endDate) {
        this.experiencePosition = experiencePosition;
        this.experiencePlace = experiencePlace;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    public String getExperiencePosition() {
        return experiencePosition;
    }

    public void setExperiencePosition(String experiencePosition) {
        this.experiencePosition = experiencePosition;
    }

    public String getExperiencePlace() {
        return experiencePlace;
    }

    public void setExperiencePlace(String experiencePlace) {
        this.experiencePlace = experiencePlace;
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
        return "Experience{" +
                "experienceId=" + experienceId +
                ", experiencePosition='" + experiencePosition + '\'' +
                ", experiencePlace='" + experiencePlace + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
