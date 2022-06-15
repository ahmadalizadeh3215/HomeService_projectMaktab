package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends GenericRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
    Users findByEmailAndPassword(String email, String password);

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Users> findAll(Specification<Users> specification);







}
