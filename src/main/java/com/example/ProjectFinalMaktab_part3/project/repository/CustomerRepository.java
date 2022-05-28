package com.example.ProjectFinalMaktab_part3.project.repository;


import com.example.ProjectFinalMaktab_part3.project.model.Customer;

import java.util.List;


public interface CustomerRepository extends GenericRepository<Customer,Integer> {
Customer findByUserEmail(String email);
void deleteByUserEmail(String email);
List<Customer> findByUserFirstName(String firstName);
List<Customer> findByUserLastName(String lastName);

}
