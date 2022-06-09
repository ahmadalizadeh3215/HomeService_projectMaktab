package com.example.ProjectFinalMaktab_part3.project.registration.token;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {
private final ConfirmationTokenRepository confirmationTokenRepository;
private ConfirmationToken confirmationToken;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository,
                                    ConfirmationToken confirmationToken ) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.confirmationToken=confirmationToken;
    }
    public void save(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token){
        return Optional.ofNullable(confirmationTokenRepository.findByToken(token));
    }
    public void setConfirmedAt(String token){
        confirmationToken=confirmationTokenRepository.findByToken(token);
        if (confirmationToken!=null){
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            confirmationTokenRepository.save(confirmationToken);
        }

    }
}
