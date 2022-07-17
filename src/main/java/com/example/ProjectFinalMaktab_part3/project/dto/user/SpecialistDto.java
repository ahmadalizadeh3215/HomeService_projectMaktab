package com.example.ProjectFinalMaktab_part3.project.dto.user;

import com.example.ProjectFinalMaktab_part3.project.model.SubTask;
import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


@Data
@DiscriminatorValue("SPECIALIST")
@ToString
public class SpecialistDto extends UserDto{
    private String skill;
    private Integer score;
    private Double validity;
    @ManyToOne
    private SubTask subTask;
    @Lob
    private byte[] personalPhoto;
}
