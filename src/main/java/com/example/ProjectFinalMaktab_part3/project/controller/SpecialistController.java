package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.registration.PasswordEncoder;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SpecialistServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/specialist")
public class SpecialistController {

    private SpecialistServiceImpl specialistService;
    private UserServiceImpl userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    private final Logger LOG = LoggerFactory.getLogger(SpecialistController.class);

    public SpecialistController(SpecialistServiceImpl specialistService,
                                UserServiceImpl userService,
                                ModelMapper modelMapper,
                                PasswordEncoder passwordEncoder) {
        this.specialistService = specialistService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Specialist>> getAllSpecialist() {

        return ResponseEntity.ok(specialistService.findAll());
    }

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<SpecialistDto> get(@PathVariable("email") String email) {
        LOG.info("getting Specialist with email: {}", email);
        Specialist specialist = specialistService.findByEmail(email);

        if (specialist == null) {
            LOG.info("user with email {} not found", email);
            return new ResponseEntity<SpecialistDto>(HttpStatus.NOT_FOUND);
        }
        SpecialistDto specialistDto = modelMapper.map(specialist, SpecialistDto.class);

        return ResponseEntity.ok().body(specialistDto);
    }

    @PostMapping(value = "/saveSpecialist")
    public ResponseEntity<SpecialistDto> create(@RequestBody SpecialistDto specialistDto) {
        LOG.info("creating new specialist: {}", specialistDto);

        if (specialistService.findByEmail(specialistDto.getEmail()) != null) {
            LOG.info("a specialist with name " + specialistDto.getEmail() + " already exists");
            return new ResponseEntity<SpecialistDto>(HttpStatus.CONFLICT);
        }
        specialistDto.setValidity(20000.0);
        specialistDto.setRole(Role.SPECIALIST);
        specialistDto.setScore(10);
        specialistDto.setSkill("General");
        specialistService.register(specialistDto);
        Specialist specialist = modelMapper.map(specialistDto, Specialist.class);
        SpecialistDto specialistDto1 = modelMapper.map(specialist, SpecialistDto.class);
        return new ResponseEntity<SpecialistDto>(specialistDto1, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update/{email}")
    public  ResponseEntity<SpecialistDto> update(@PathVariable("email") String email, @RequestBody SpecialistDto  specialistDto) {

        LOG.info("updating specialist: {}", specialistDto);
        Specialist specialist = specialistService.findByEmail(email);
        if (specialist == null) {
            LOG.info("User with email {} not found", email);
            return new ResponseEntity<SpecialistDto>(HttpStatus.NOT_FOUND);
        }
        specialist.setFirstName(specialistDto.getFirstName());
        specialist.setLastName(specialistDto.getLastName());
        specialist.setEmail(specialistDto.getEmail());
        specialist.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(specialistDto.getPassword()));
        specialistService.update(specialist);

        SpecialistDto specialistDto1=modelMapper.map(specialist,SpecialistDto.class);
        return new ResponseEntity<SpecialistDto>(specialistDto1, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        LOG.info("deleting specialist with email: {}", email);
        Specialist specialist = specialistService.findByEmail(email);

        if (specialist == null) {
            LOG.info("Unable to delete. specialist with email {} not found", email);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        specialistService.delete(specialist);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
