package com.example.ProjectFinalMaktab_part3.project.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ConfirmationTokenRepository
        extends JpaRepository<ConfirmationToken, Long> {
   ConfirmationToken findByToken(String token);


}
