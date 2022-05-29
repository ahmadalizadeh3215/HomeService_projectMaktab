package com.example.ProjectFinalMaktab_part3.project.service.serviceImpl;

import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl  {

   private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
