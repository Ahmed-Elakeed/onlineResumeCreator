package com.example.onlineresumecreator.service;

import com.example.onlineresumecreator.model.Course;
import com.example.onlineresumecreator.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ServiceInterface<Course> {
    private CourseRepository courseRepository;

    private UserService userService;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public List<Course> getAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Course getById(Long id) {
        return this.courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course save(Course course) {
        course.setUser(this.userService.getCurrentUser());
        return this.courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        course.setCourseId(id);
        course.setUser(this.userService.getCurrentUser());
        return this.courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id) {
        this.userService.getCurrentUser().getCourses().remove(this.courseRepository.getById(id));
    }
}
