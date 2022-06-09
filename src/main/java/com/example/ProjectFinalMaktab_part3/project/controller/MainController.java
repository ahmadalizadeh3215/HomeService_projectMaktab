package com.example.ProjectFinalMaktab_part3.project.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
    @RequestMapping("/index")
    public String index(){
        return "home/index";
    }

@GetMapping(value = "/login")
    public String loginPage(){
        return "login";
    }
}
