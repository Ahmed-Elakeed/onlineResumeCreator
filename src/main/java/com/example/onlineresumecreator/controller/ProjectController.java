package com.example.onlineresumecreator.controller;


import com.example.onlineresumecreator.model.Project;
import com.example.onlineresumecreator.service.ProjectService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "/adding")
    public String getAddPage(Model model) {
        model.addAttribute("project", new Project());
        return "project_add";
    }

    @PostMapping(path = "/save")
    public String saveUserProject(@ModelAttribute("project") Project project) {
        this.projectService.save(project);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("project", this.projectService.getById(id));
        return "project_edit";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUserProject(@PathVariable("id") Long id, @ModelAttribute("project") Project project) {
        this.projectService.update(id, project);
        return "redirect:/users/resume";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUserProject(@PathVariable("id") Long id) {
        this.projectService.deleteProject(id);
        return "redirect:/users/resume";
    }
}
