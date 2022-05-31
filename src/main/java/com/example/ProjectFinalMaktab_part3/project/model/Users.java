package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "this filed cannot empty")
    private String firstName;
    @NotNull(message = "this filed cannot empty")
    private String lastName;
    @Column(unique = true)
    @Email(message = "invalid your email")
    private String email;
    @Column( nullable = false)
    @Min(value = 8,message = "invalid your input")
    @NotNull(message = "this filed cannot empty")
    private String password;
    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "authoritises",joinColumns =
    @JoinColumn(name = "email",referencedColumnName = "email"))
    private List<Role> role;
    @CreationTimestamp
    private LocalDateTime registrationTime;
    private Boolean enabled=true;


}
