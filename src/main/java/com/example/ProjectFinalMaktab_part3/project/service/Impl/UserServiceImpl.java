package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.StatusUser;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.registration.ValidationPassword;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationToken;
import com.example.ProjectFinalMaktab_part3.project.registration.token.ConfirmationTokenService;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import com.example.ProjectFinalMaktab_part3.project.gridSearch.UserGridSearch;
import com.example.ProjectFinalMaktab_part3.project.service.UserService;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Getter
public class UserServiceImpl extends GenericServiceImpl<Users, Integer> implements UserService, UserDetailsService {


    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Users users;
    private UserRepository userRepository;
    private ValidationPassword validationPassword;
    private ConfirmationTokenService confirmationTokenService;
    private UserGridSearch userGridSearch;

    public UserServiceImpl(GenericRepository<Users, Integer> genericRepository,
                           Users users, UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ValidationPassword validationPassword,
                           ConfirmationTokenService confirmationTokenService,
                           UserGridSearch userGridSearch) {
        super(genericRepository);
        this.users = users;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.validationPassword = validationPassword;
        this.confirmationTokenService=confirmationTokenService;
        this.userGridSearch=userGridSearch;
    }

    @Override
    public Users findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public Users save(Users entity) {
        entity.setStatusUser(StatusUser.AwaitingApproval);
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        entity.setRegistrationTime(LocalDateTime.now());
        return userRepository.save(entity);
    }

    private final static String USER_NOT_FOUND = "User with email %s not Found ";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String sinUpUser(Users users) {
        boolean userExists = userRepository.findByEmail(users.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("user already exists");
        }

        boolean isValidPass = validationPassword.isValidPassword(users.getPassword());
        if (!isValidPass) {
            throw new IllegalStateException("password invalid");
        }
        String encodePassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodePassword);
        userRepository.save(users);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=new ConfirmationToken(
                token,LocalDateTime.now(),LocalDateTime.now().plusMinutes(10),users);
        confirmationTokenService.save(confirmationToken);
        return token;

    }
    public void enableUser(String email){
        Users users=userRepository.findByEmail(email).orElseThrow();
       users.setEnabled(true);
       userRepository.save(users);

    };

    @Override
    public Users changePassword(String email, String password) {
        Users users=userRepository.findByEmail(email).orElse(null);
        if (users !=null){
            boolean isValidPass = validationPassword.isValidPassword(password);
            if (!isValidPass) {
                throw new IllegalStateException("password invalid");
            }
            String encodePassword = bCryptPasswordEncoder.encode(users.getPassword());
            users.setPassword(encodePassword);
            update(users);

        }

        return users;
    }

    @Override
    public List<Users> gridSearch(Integer id, String email, String firstName, String lastName) {
        Specification<Users> specification = userGridSearch.gridSearch(id, email, firstName, lastName);
        return userRepository.findAll(specification);
    }
}
