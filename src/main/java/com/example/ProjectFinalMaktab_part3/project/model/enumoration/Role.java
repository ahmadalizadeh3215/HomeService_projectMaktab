package com.example.ProjectFinalMaktab_part3.project.model.enumoration;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER,
    ADMIN,
    SPECIALIST;


    @Override
    public String getAuthority() {
        return this.name();
    }
}