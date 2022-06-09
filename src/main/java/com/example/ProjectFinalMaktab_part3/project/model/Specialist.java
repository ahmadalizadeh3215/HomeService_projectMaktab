package com.example.ProjectFinalMaktab_part3.project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("SPECIALIST")
public class Specialist extends Users{

    private String skill;
    private Integer score;
    private Double validity;
    @ManyToOne
    private SubTask subTask;
    @Lob
    private byte[] personalPhoto;


    public Specialist(String firstName, String lastName, String email,
                      String password,  List<Role> role,
                      String skill, Integer score, Double validity, SubTask subTask,
                      byte[] personalPhoto) {
        super(firstName, lastName, email, password, role);
        this.skill = skill;
        this.score = score;
        this.validity = validity;
        this.subTask = subTask;
        this.personalPhoto = personalPhoto;
    }
}


