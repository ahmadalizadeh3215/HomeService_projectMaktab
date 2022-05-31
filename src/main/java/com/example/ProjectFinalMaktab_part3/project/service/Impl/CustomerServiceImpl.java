package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.repository.CustomerRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer> implements CustomerService {

    private Customer customer;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(GenericRepository<Customer, Integer> genericRepository
            , Customer customer, CustomerRepository customerRepository) {
        super(genericRepository);
        this.customer = customer;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByUserEmail(String email) {
        return null;
    }

    @Override
    public void deleteByUserEmail(String email) {

    }

    @Override
    public List<Customer> findByUserFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Customer> findByUserLastName(String lastName) {
        return null;
    }
}
