package com.example.onlineresumecreator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Integer userAge;
    @Column(unique = true)
    private String userPhone;
    private String userJobTitle;
    private String userCountry;
    private String userCity;
    @Column(unique = true)
    private String userEmail;

    private String userPassword;


    @Column(length = 800)
    private String userAbout;


    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private Set<Course> courses;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Skill> skills;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Project> projects;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Education> educations;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Experience> experiences;

    public User() {
    }

    public User(String userFirstName, String userLastName, Integer userAge, String userPhone, String useJobTitle, String userCountry, String userCity, String userEmail, String userPassword, String userAbout) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userJobTitle = useJobTitle;
        this.userCountry = userCountry;
        this.userCity = userCity;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAbout = userAbout;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserJobTitle() {
        return userJobTitle;
    }

    public void setUserJobTitle(String userJobTitle) {
        this.userJobTitle = userJobTitle;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String country) {
        this.userCountry = country;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String city) {
        this.userCity = city;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Education> getEducations() {
        return educations;
    }

    public void setEducations(Set<Education> educations) {
        this.educations = educations;
    }

    public Set<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Set<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userJobTitle='" + userJobTitle + '\'' +
                ", country='" + userCountry + '\'' +
                ", city='" + userCity + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", courses=" + courses +
                ", skills=" + skills +
                ", projects=" + projects +
                ", educations=" + educations +
                ", experiences=" + experiences +
                '}';
    }
}
