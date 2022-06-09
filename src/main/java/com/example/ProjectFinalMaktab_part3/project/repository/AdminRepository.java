package com.example.ProjectFinalMaktab_part3.project.repository;



import com.example.ProjectFinalMaktab_part3.project.model.Admin;

import java.util.List;

public interface AdminRepository extends GenericRepository<Admin,Integer>  {
    Admin findByEmail(String email);
    void deleteByEmail(String email);
    List<Admin> findByFirstName(String firstName);
    List<Admin> findByLastName(String lastName);


}
