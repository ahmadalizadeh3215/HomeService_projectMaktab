package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Users;

import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Qualifier("UserValidator")
    private Validator validator;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }


    @RequestMapping("/findAllUsers")
    public String findAllUsers(Model model) {
        List<Users> users = userService.findAll();
        model.addAttribute("findAllUsers", users);
        return "findAllUsers";
    }

    @GetMapping(value = "/addNew")
     public String register(Model model) {
         Users users = new Users();
         model.addAttribute("users", users);

         return "register";
     }


    @PostMapping(value = "/register")
    public String saveUsers(@ModelAttribute @Validated Users users, BindingResult bindingResult, Model model) {
        model.addAttribute("users", users);
        if (userService.existsById(users.getId()) || users.getPassword()==null){
            return "/users/addNew";
        }


        users.setRole(Collections.singletonList(Role.SPECIALIST));
        userService.save(users);
        return "redirect:/users/findAllUsers";
    }




}
