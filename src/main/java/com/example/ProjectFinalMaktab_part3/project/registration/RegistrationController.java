package com.example.ProjectFinalMaktab_part3.project.registration;


import com.example.ProjectFinalMaktab_part3.project.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/registrationUser")
public class RegistrationController {

    private RegistrationService registrationService;


    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(value = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        System.out.println("controller");
        return registrationService.confirmToken(token);
    }


    @PostMapping()
    public String register(@RequestBody UserDto userDto) {
        return registrationService.register(userDto);
    }


}
