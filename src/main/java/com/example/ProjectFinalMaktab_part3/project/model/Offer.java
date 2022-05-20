package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dateSubmission;
    private Double proposedPrice;
    private String durationOfWork;
    private LocalDateTime startTime;
    @ManyToOne
    private Specialist specialist;
    @ManyToOne
    private Orders orders;
}
