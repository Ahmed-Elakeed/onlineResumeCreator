package com.example.onlineresumecreator.controller;

import com.example.onlineresumecreator.model.Skill;
import com.example.onlineresumecreator.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/skill")
public class SkillController {

    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping(path = "/adding")
    public String getAddPage(Model model) {
        model.addAttribute("skill", new Skill());
        return "skill_add";
    }

    @PostMapping(path = "/save")
    public String saveUserSkill(@ModelAttribute("skill") Skill skill) {
        this.skillService.save(skill);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserSkill(@PathVariable("id") Long id) {
        this.skillService.deleteSkill(id);
        return "redirect:/users/resume";
    }
}
