package com.example.ProjectFinalMaktab_part3.project.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
@Data
@DiscriminatorValue("CUSTOMER")
@ToString
public class CustomerDto extends UserDto {
    private Double validity;

}
