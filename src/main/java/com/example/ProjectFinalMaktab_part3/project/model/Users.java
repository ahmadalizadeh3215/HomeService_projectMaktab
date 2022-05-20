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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(length = 8, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;
    private LocalDateTime registrationTime;

    @Override
    public String toString() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", statusUser=" + statusUser +
                ", typeUser=" + typeUser +
                ", registrationTime=" + registrationTime ;

    }
}
