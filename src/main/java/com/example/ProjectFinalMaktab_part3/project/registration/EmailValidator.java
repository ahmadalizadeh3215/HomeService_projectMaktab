package com.example.ProjectFinalMaktab_part3.project.registration;

import org.springframework.stereotype.Service;


@Service
public class EmailValidator {
    public boolean test(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
