package com.example.ProjectFinalMaktab_part3.project.service;


import com.example.ProjectFinalMaktab_part3.project.model.Users;

import java.util.Optional;


public interface UserService {

    Users findByEmailAndPassword(String email, String password);

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
}
