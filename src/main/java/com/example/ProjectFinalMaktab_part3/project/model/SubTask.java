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
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String description;
    private Double basePrice;
    @ManyToOne
    private Tasks tasks;
    @ManyToOne
    private Specialist specialist;


}
