package com.example.onlineresumecreator.controller;

import com.example.onlineresumecreator.model.User;
import com.example.onlineresumecreator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/resume")
    @Transactional
    public String getResumePage(Model model, Principal principal) {
        User user = userService.findUserByUserEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("educations", user.getEducations());
        model.addAttribute("experiences", user.getExperiences());
        model.addAttribute("projects", user.getProjects());
        model.addAttribute("courses", user.getCourses());
        model.addAttribute("skills", user.getSkills());
        return "resume";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(path = "/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(path = "/register")
    public String userRegister(@ModelAttribute("user") User user, @RequestParam String repeat_password) {
        if (this.userService.registerUser(user, repeat_password))
            return "login";
        return "register";
    }

    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.getById(id));
        return "user_edit";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, @RequestParam("repeat_password") String repeat_password) {
        if (user.getUserPassword().equals(repeat_password)) {
            if (this.userService.update(id, user) == null) {
                return "redirect:/users/edit/{id}";
            }
            return "redirect:/logout";
        }
        return "redirect:/users/edit/id";
    }

    @GetMapping(path = "/remove/{id}")
    public String getDeleteConfirmPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", this.userService.getById(id));
        return "user_delete";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return "redirect:/logout";
    }

    @GetMapping(path = "/about/adding")
    public String getAboutAddPage(Model model) {
        model.addAttribute("user", this.userService.getCurrentUser());
        return "about_add";
    }

    @PostMapping(path = "/about/save")
    @Transactional
    public String saveUserAbout(@ModelAttribute("user") User user) {
        this.userService.getCurrentUser().setUserAbout(user.getUserAbout());
        return "redirect:/users/resume";
    }
}
