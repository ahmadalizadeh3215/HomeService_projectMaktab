package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.StatusUser;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.UserRepository;
import com.example.ProjectFinalMaktab_part3.project.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends GenericServiceImpl<Users, Integer> implements UserService , UserDetailsService {


    private UserRepository userRepository;
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Users users;

    public UserServiceImpl(GenericRepository<Users, Integer> genericRepository, UserRepository userRepository
            , BCryptPasswordEncoder bCryptPasswordEncoder, Users users) {
        super(genericRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.users = users;
    }



    @Override
    public Users findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public Users findByEmail(String email) {
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}
