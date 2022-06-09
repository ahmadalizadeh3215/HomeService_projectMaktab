package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.email.EmailSender;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.EmailValidator;
import com.example.ProjectFinalMaktab_part3.project.registration.RegistrationService;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationTokenService;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.SpecialistRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import com.example.ProjectFinalMaktab_part3.project.service.SpecialistService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialistServiceImpl extends GenericServiceImpl<Specialist, Integer> implements SpecialistService {
    private Specialist specialist;
    private SpecialistRepository specialistRepository;
    public UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private UserServiceImpl userService;
    private RegistrationService registrationService;

    public SpecialistServiceImpl(GenericRepository<Specialist,
                                 Integer> genericRepository,
                                 Specialist specialist,
                                 SpecialistRepository specialistRepository,
                                 UserRepository userRepository,
                                 EmailValidator emailValidator,
                                 ConfirmationTokenService confirmationTokenService,
                                 EmailSender emailSender,
                                 UserServiceImpl userService,
                                 RegistrationService registrationService
    ) {
        super(genericRepository);
        this.specialist = specialist;
        this.specialistRepository = specialistRepository;
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.userService = userService;
        this.registrationService = registrationService;

    }

    @Override
    public Specialist findByEmail(String email) {
        return specialistRepository.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        specialistRepository.deleteByEmail(email);

    }

    @Override
    public Specialist findByFirstName(String firstName) {
        return specialistRepository.findByFirstName(firstName);
    }

    @Override
    public Specialist findByLastName(String lastName) {
        return specialistRepository.findByLastName(lastName);
    }

    @Override
    public Specialist findSpecialistBySubTaskId(Integer subTaskId) {
        return specialistRepository.findSpecialistBySubTaskId(subTaskId);
    }

    @Override
    public List<Specialist> findSpecialistBySkill(String skill) {
        return specialistRepository.findSpecialistBySkill(skill);
    }

    @Override
    public List<Specialist> findByOrderByScore(Specialist specialist, Sort sort) {
        return specialistRepository.findByOrderByScore(specialist, sort);
    }

    public String register(SpecialistDto specialistDto) {
        Boolean isValidatorEmail = emailValidator
                .test(specialistDto.getEmail());
        if (!isValidatorEmail) {
            throw new IllegalStateException("email not valid.");
        }
        String token = userService
                .sinUpUser(new Specialist(
                        specialistDto.getFirstName(),
                        specialistDto.getLastName(),
                        specialistDto.getEmail(),
                        specialistDto.getPassword(),
                        Collections.singletonList(specialistDto.getRole()),
                        specialistDto.getSkill(),
                        specialistDto.getScore(),
                        specialistDto.getValidity(),
                        specialistDto.getSubTask(),
                        specialistDto.getPersonalPhoto()));
        String link = "http://localhost:8081/registrationUser/confirm?token=" + token;
        emailSender.send(specialistDto.getEmail(),
                registrationService.buildEmail(specialistDto.getFirstName(), link));
        return token;
    }


}
