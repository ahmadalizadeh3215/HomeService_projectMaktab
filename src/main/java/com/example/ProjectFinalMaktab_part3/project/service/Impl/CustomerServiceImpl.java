package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.dto.CustomerDto;
import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.email.EmailSender;
import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.registration.EmailValidator;
import com.example.ProjectFinalMaktab_part3.project.registration.RegistrationService;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationTokenService;
import com.example.ProjectFinalMaktab_part3.project.repository.CustomerRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer> implements CustomerService {

    private Customer customer;
    private CustomerRepository customerRepository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private UserServiceImpl userService;
    private RegistrationService registrationService;

    public CustomerServiceImpl(GenericRepository<Customer,
                              Integer> genericRepository,
                              Customer customer, CustomerRepository customerRepository,
                               EmailValidator emailValidator,
                               ConfirmationTokenService confirmationTokenService,
                               EmailSender emailSender,
                               UserServiceImpl userService,
                               RegistrationService registrationService) {
        super(genericRepository);
        this.customer = customer;
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        customerRepository.deleteByEmail(email);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public List<Customer> findByRole(Role role) {
        return customerRepository.findByRole(role);
    }

    public String register(CustomerDto customerDto) {
        Boolean isValidatorEmail = emailValidator
                .test(customerDto.getEmail());
        if (!isValidatorEmail) {
            throw new IllegalStateException("email not valid.");
        }
        String token = userService
                .sinUpUser(new Customer(
                        customerDto.getFirstName(),
                        customerDto.getLastName(),
                        customerDto.getEmail(),
                        customerDto.getPassword(),
                        Collections.singletonList(customerDto.getRole()),
                        customerDto.getValidity()
                        ));
        String link = "http://localhost:8081/registrationUser/confirm?token=" + token;
        emailSender.send(customerDto.getEmail(),
                registrationService.buildEmail(customerDto.getFirstName(), link));
        return token;
    }

}
