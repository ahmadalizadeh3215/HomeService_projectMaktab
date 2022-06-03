package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;



@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Mandatory field")
    private String firstName;
    @NotBlank(message = "Mandatory field")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Mandatory field")
    private String email;
    @Column( nullable = false)
    @Min(value = 8,message = "The password is at least 8 digits")
    @NotBlank (message = "Mandatory field")
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
    private Boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
