package com.example.onlineresumecreator.controller;


import com.example.onlineresumecreator.model.Experience;
import com.example.onlineresumecreator.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/experience")
public class ExperienceController {
    private ExperienceService experienceService;

    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping(path = "/adding")
    public String getAddPage(Model model) {
        model.addAttribute("experience", new Experience());
        return "experience_add";
    }

    @PostMapping(path = "/save")
    public String saveUserExperience(@ModelAttribute("experience") Experience experience,
                                     @RequestParam(required = false, value = "still_working") String still_working) {
        if (still_working != null) {
            experience.setEndDate("Present");
        }
        this.experienceService.save(experience);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("experience", this.experienceService.getById(id));
        return "experience_edit";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUserExperience(@PathVariable("id") Long id,
                                       @ModelAttribute("experience") Experience experience,
                                       @RequestParam(required = false, value = "still_working") String still_working) {
        if (still_working != null) {
            experience.setEndDate("Present");
        }
        this.experienceService.update(id, experience);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserExperience(@PathVariable("id") Long id) {
        this.experienceService.deleteExperience(id);
        return "redirect:/users/resume";
    }
}
