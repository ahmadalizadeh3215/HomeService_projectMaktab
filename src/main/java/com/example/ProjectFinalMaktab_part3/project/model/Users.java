package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(length = 8, nullable = false)
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
