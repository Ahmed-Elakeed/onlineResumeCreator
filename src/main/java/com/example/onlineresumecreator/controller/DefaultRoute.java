package com.example.onlineresumecreator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class DefaultRoute {
    @GetMapping
    public String getResumePage(){
        return "redirect:/users/resume";
    }
}
