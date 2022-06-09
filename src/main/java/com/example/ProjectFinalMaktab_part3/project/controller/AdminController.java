package com.example.ProjectFinalMaktab_part3.project.controller;


import com.example.ProjectFinalMaktab_part3.project.dto.AdminDto;
import com.example.ProjectFinalMaktab_part3.project.dto.UserDto;
import com.example.ProjectFinalMaktab_part3.project.model.Admin;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.PasswordEncoder;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.AdminServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private AdminServiceImpl adminService;
    private UserServiceImpl userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    private final Logger LOG = LoggerFactory.getLogger(SpecialistController.class);

    public AdminController(AdminServiceImpl adminService,
                           UserServiceImpl userService,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/findAll")
    public List<AdminDto> getAllAdmin() {

        return adminService.findAll().stream().map(Admin -> modelMapper.map(Admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/findByEmail/{email}")
    public ResponseEntity<AdminDto> get(@PathVariable("email") String email) {
        LOG.info("getting admin with email: {}", email);
        Admin admin = adminService.findByEmail(email);

        if (admin == null) {
            LOG.info("user with email {} not found", email);
            return new ResponseEntity<AdminDto>(HttpStatus.NOT_FOUND);
        }
        AdminDto adminDto = modelMapper.map(admin, AdminDto.class);

        return ResponseEntity.ok().body(adminDto);
    }

    @PostMapping(value = "/saveAdmin")
    public ResponseEntity<AdminDto> create(@RequestBody AdminDto adminDto) {
        LOG.info("creating new admin: {}", adminDto);

        if (adminService.findByEmail(adminDto.getEmail())!=null) {
            LOG.info("a admin with name " + adminDto.getEmail() + " already exists");
            return new ResponseEntity<AdminDto>(HttpStatus.CONFLICT);
        }

        adminDto.setRole(Role.ADMIN);
        adminService.register(adminDto);
        Admin admin = modelMapper.map(adminDto, Admin.class);
        AdminDto adminDto1= modelMapper.map(admin, AdminDto.class);
        return new ResponseEntity<AdminDto>(adminDto1, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update/{email}")
    public  ResponseEntity<AdminDto> update(@PathVariable("email") String email, @RequestBody AdminDto adminDto) {

        LOG.info("updating admin: {}", adminDto);
        Admin admin = adminService.findByEmail(email);
        if (admin == null) {
            LOG.info("User with email {} not found", email);
            return new ResponseEntity<AdminDto>(HttpStatus.NOT_FOUND);
        }
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(adminDto.getPassword()));
        adminService.update(admin);
       AdminDto adminDto1=modelMapper.map(admin,AdminDto.class);
        return new ResponseEntity<AdminDto>(adminDto1, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{email}")
    public ResponseEntity<Void> delete(@PathVariable("email") String email) {
        LOG.info("deleting admin with email: {}", email);
        Admin admin = adminService.findByEmail(email);

        if (admin == null) {
            LOG.info("Unable to delete. admin with email {} not found", email);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        userService.delete(admin);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
