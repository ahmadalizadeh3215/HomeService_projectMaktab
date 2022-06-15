package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Users;

import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;

    }


    @GetMapping(value = "findByEmail/{email}")
    public ResponseEntity<Users> get(@PathVariable("email") String email) {
        LOG.info("getting user with email: {}", email);
        Optional<Users> user = userService.findByEmail(email);

        if (user == null) {
            LOG.info("user with email {} not found", email);
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Users>(HttpStatus.OK);
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<Void> create(@RequestBody Users user, UriComponentsBuilder ucBuilder) {
        LOG.info("creating new user: {}", user);

        if (userService.existsByEmail(user.getEmail())) {
            LOG.info("a user with name " + user.getEmail() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }


    @GetMapping(value = "/findAllUsers")
    public ResponseEntity<List<Users>> getAll() {
        LOG.info("getting all Users");
        List<Users> users = userService.findAll();

        if (users == null || users.isEmpty()) {
            LOG.info("no users found");
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }
    @GetMapping(value = "/gridSreach/")
    public ResponseEntity<List<Users>> gridSreach(@RequestParam(required = false, name = "id") Integer id,
                                                  @RequestParam(required = false, name = "email") String email,
                                                  @RequestParam(required = false, name = "firstName") String firstName,
                                                  @RequestParam(required = false, name = "lastName") String lastName) {

        List<Users> users = userService.gridSearch(id,email,firstName,lastName);

        return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
    }

}



