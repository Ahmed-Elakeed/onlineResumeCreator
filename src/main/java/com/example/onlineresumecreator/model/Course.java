package com.example.onlineresumecreator.model;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseProvider;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Course() {
    }

    public Course(String courseName, String courseProvider) {
        this.courseName = courseName;
        this.courseProvider = courseProvider;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseProvider() {
        return courseProvider;
    }

    public void setCourseProvider(String courseProvider) {
        this.courseProvider = courseProvider;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseProvider='" + courseProvider + '\'' +
                '}';
    }
}
