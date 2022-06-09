package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.Role;

import java.util.List;

public interface CustomerService extends GenericService<Customer, Integer> {

    Customer findByEmail(String email);

    void deleteByEmail(String email);

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
    Boolean existsByEmail(String email);
    List<Customer> findByRole(Role role);
}
