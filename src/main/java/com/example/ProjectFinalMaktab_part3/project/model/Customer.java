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
@Data
@DiscriminatorValue("CUSTOMER")
public class Customer extends Users{

    private Double validity;

    public Customer(String firstName, String lastName,
                    String email, String password, List<Role> role,
                    Double validity) {
        super(firstName, lastName, email, password, role);
        this.validity = validity;
    }
}
