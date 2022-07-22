package com.example.ProjectFinalMaktab_part3.project.dto.user;

import com.example.ProjectFinalMaktab_part3.project.model.enumoration.Role;
import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
public class CustomerDto  {

    @NotNull(message = "This field is mandatory")
    private String firstName;

    @NotNull(message = "This field is mandatory")
    private String lastName;

    @NotNull(message = "This field is mandatory")
    @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$",
            message = "The email entered is incorrect")
    private String email;

    @NotNull(message = "This field is mandatory")
    @Min(value = 8,message = "The password must be at least 8 digits long")
    private String password;

    @NotNull(message = "This field is mandatory")
    private Double validity;


}
