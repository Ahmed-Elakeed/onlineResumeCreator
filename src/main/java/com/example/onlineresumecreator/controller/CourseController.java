package com.example.onlineresumecreator.controller;

import com.example.onlineresumecreator.model.Course;
import com.example.onlineresumecreator.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "/adding")
    public String getAddPage(Model model) {
        model.addAttribute("course", new Course());
        return "course_add";
    }

    @PostMapping(path = "/save")
    public String saveUserCourse(@ModelAttribute("course") Course course) {
        this.courseService.save(course);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", this.courseService.getById(id));
        return "course_edit";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUserCourse(@PathVariable("id") Long id, @ModelAttribute("course") Course course) {
        this.courseService.update(id, course);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserCourse(@PathVariable("id") Long id){
        this.courseService.deleteCourse(id);
        return "redirect:/users/resume";
    }
}
