package com.example.ProjectFinalMaktab_part3.project.dto.user;

import com.example.ProjectFinalMaktab_part3.project.model.enumoration.Role;
import lombok.Data;
import javax.persistence.*;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role",
        discriminatorType = DiscriminatorType.STRING)
public class UserDto  {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;


}
