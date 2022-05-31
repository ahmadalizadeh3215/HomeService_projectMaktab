package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.Customer;

import java.util.List;

public interface CustomerService extends GenericService<Customer, Integer> {

    Customer findByUserEmail(String email);

    void deleteByUserEmail(String email);

    List<Customer> findByUserFirstName(String firstName);

    List<Customer> findByUserLastName(String lastName);
}
