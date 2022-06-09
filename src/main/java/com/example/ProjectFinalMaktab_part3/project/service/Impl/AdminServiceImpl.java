package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.dto.AdminDto;
import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.dto.UserDto;
import com.example.ProjectFinalMaktab_part3.project.email.EmailSender;
import com.example.ProjectFinalMaktab_part3.project.model.Admin;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.EmailValidator;
import com.example.ProjectFinalMaktab_part3.project.registration.RegistrationService;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationTokenService;
import com.example.ProjectFinalMaktab_part3.project.repository.AdminRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import com.example.ProjectFinalMaktab_part3.project.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl extends GenericServiceImpl<Admin, Integer> implements AdminService {
    private Admin admin;
    private AdminRepository adminRepository;
    public UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private UserServiceImpl userService;
    private RegistrationService registrationService;

    public AdminServiceImpl(GenericRepository<Admin, Integer> genericRepository
            , Admin admin, AdminRepository adminRepository,
                            UserRepository userRepository,
                            EmailValidator emailValidator,
                            ConfirmationTokenService confirmationTokenService,
                            EmailSender emailSender,
                            UserServiceImpl userService,
                            RegistrationService registrationService) {
        super(genericRepository);
        this.admin = admin;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.userService = userService;
        this.registrationService = registrationService;
    }


    @Override
    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
adminRepository.deleteByEmail(email);
    }

    @Override
    public List<Admin> findByFirstName(String firstName) {
        return adminRepository.findByFirstName(firstName);
    }

    @Override
    public List<Admin> findByLastName(String lastName) {
        return adminRepository.findByLastName(lastName);
    }
    public String register(AdminDto adminDto) {
        Boolean isValidatorEmail = emailValidator
                .test(adminDto.getEmail());
        if (!isValidatorEmail) {
            throw new IllegalStateException("email not valid.");
        }
        String token = userService
                .sinUpUser(new Admin(
                        adminDto.getFirstName(),
                        adminDto.getLastName(),
                        adminDto.getEmail(),
                        adminDto.getPassword(),
                        Collections.singletonList(adminDto.getRole())));
        String link = "http://localhost:8081/registrationUser/confirm?token=" + token;
        emailSender.send(adminDto.getEmail(),
                registrationService.buildEmail(adminDto.getFirstName(), link));
        return token;
    }


}
