package com.example.ProjectFinalMaktab_part3.project.dto.user;

import lombok.Data;
import javax.persistence.DiscriminatorValue;

@Data
@DiscriminatorValue("ADMIN")
public class AdminDto extends UserDto{

}
