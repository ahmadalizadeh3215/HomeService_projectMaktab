package com.example.ProjectFinalMaktab_part3.project.controller;


import com.example.ProjectFinalMaktab_part3.project.dto.CustomerDto;
import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.PasswordEncoder;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.CustomerServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SpecialistServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private CustomerServiceImpl customerService;
    private UserServiceImpl userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private final Logger LOG = LoggerFactory.getLogger(SpecialistController.class);

    public CustomerController(CustomerServiceImpl customerService,
                              UserServiceImpl userService,
                              ModelMapper modelMapper,
                              PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/findAll")
    public List<CustomerDto> getAllSpecialist() {

        return customerService.findAll().stream().map(Customer -> modelMapper.map(Customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<CustomerDto> get(@PathVariable("email") String email) {
        LOG.info("getting customer with email: {}", email);
        Customer customer = customerService.findByEmail(email);

        if (customer == null) {
            LOG.info("user with email {} not found", email);
            return new ResponseEntity<CustomerDto>(HttpStatus.NOT_FOUND);
        }
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);

        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto) {
        LOG.info("creating new customer: {}", customerDto);

        if (customerService.findByEmail(customerDto.getEmail()) != null) {
            LOG.info("a customer with name " + customerDto.getEmail() + " already exists");
            return new ResponseEntity<CustomerDto>(HttpStatus.CONFLICT);
        }
        customerDto.setValidity(10000.0);
        customerDto.setRole(Role.CUSTOMER);
        customerService.register(customerDto);
        Customer customer = modelMapper.map(customerDto, Customer.class);
        CustomerDto customerDto1 = modelMapper.map(customer, CustomerDto.class);
        return new ResponseEntity<CustomerDto>(customerDto1, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update/{email}")
    public ResponseEntity<CustomerDto> update(@PathVariable("email") String email, @RequestBody CustomerDto customerDto) {

        LOG.info("updating customer: {}", customerDto);
        Customer customer = customerService.findByEmail(email);
        if (customer == null) {
            LOG.info("User with email {} not found", email);
            return new ResponseEntity<CustomerDto>(HttpStatus.NOT_FOUND);
        }
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(customerDto.getPassword()));
        customer.setValidity(customerDto.getValidity());
        customerService.update(customer);

        CustomerDto customerDto1 = modelMapper.map(customer, CustomerDto.class);
        return new ResponseEntity<CustomerDto>(customerDto1, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        LOG.info("deleting customer with email: {}", email);
        Customer customer = customerService.findByEmail(email);

        if (customer == null) {
            LOG.info("Unable to delete. customer with email {} not found", email);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        customerService.delete(customer);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
