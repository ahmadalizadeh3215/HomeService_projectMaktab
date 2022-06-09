package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Users{

    public Admin(String firstName, String lastName,
                 String email, String password, List<Role> role) {
        super(firstName, lastName, email, password, role);
    }
}
