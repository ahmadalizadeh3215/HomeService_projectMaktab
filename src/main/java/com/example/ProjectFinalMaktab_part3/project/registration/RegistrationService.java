package com.example.ProjectFinalMaktab_part3.project.registration;

import com.example.ProjectFinalMaktab_part3.project.dto.UserDto;
import com.example.ProjectFinalMaktab_part3.project.email.EmailSender;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.StatusUser;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationToken;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationTokenService;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class RegistrationService {
    private final UserServiceImpl userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public RegistrationService(UserServiceImpl userService,
                               EmailValidator emailValidator,
                               ConfirmationTokenService confirmationTokenService,
                               EmailSender emailSender) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
    }

    public String register(UserDto userDto) {
        Boolean isValidatorEmail = emailValidator
                .test(userDto.getEmail());
        if (!isValidatorEmail) {
            throw new IllegalStateException("email not valid.");
        }
        String token = userService
                .sinUpUser(new Users(userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getEmail(),
                        userDto.getPassword(),
                        Collections.singletonList(userDto.getRole())));
        String link = "http://localhost:8081/registrationUser/confirm?token=" + token;
        emailSender.send(userDto.getEmail(), buildEmail(userDto.getFirstName(), link));
        return token;
    }

    public String buildEmail(String name, String link) {

        return ("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "hi \n" +name +"  <br/>"+
                "\n" +
              "<a href="+ link +" >Verify</a><br/>"+
                " Email validity is 10 minutes."+

                "</body>\n" +
                "</html>" );
    }

    @Transactional
    public String confirmToken(String token) {
        System.out.println("method confirm token");
        ConfirmationToken confirmationToken = confirmationTokenService.
                getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expireAt = confirmationToken.getExpiredAt();
        if (expireAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());

        return "confirmed  ";
    }



}
