package com.example.onlineresumecreator.controller;

import com.example.onlineresumecreator.model.Education;
import com.example.onlineresumecreator.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/education")
public class EducationController {

    private EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping(path = "/adding")
    public String getAddPage(Model model) {
        model.addAttribute("education", new Education());
        return "education_add";
    }

    @PostMapping(path = "/save")
    public String saveUserEducation(@ModelAttribute("education") Education education) {
        this.educationService.save(education);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("education", educationService.getById(id));
        return "education_edit";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUserEducation(@PathVariable("id") Long id, @ModelAttribute("education") Education education) {
        this.educationService.update(id, education);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserEducation(@PathVariable("id") Long id) {
        educationService.deleteEducation(id);
        return "redirect:/users/resume";
    }
}
