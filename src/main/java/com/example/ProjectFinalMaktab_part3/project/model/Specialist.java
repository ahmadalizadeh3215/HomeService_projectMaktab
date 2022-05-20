package com.example.ProjectFinalMaktab_part3.project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Specialist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Users user;
    private String skill;
    private Integer score;
    private Double validity;
    @ManyToOne
    private SubTask subTask;
    @Lob
    private byte[] personalPhoto;

}


