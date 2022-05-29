package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @RequestMapping("/findAllUsers")
    public String findAllUsers(Model model) {
        List<Users> users = userRepository.findAll();
        model.addAttribute("findAllUsers", users);
        return "findAllUsers";
    }

}
