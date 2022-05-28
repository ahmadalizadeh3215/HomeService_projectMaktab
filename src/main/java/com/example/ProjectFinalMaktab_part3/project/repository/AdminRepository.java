package com.example.ProjectFinalMaktab_part3.project.repository;



import com.example.ProjectFinalMaktab_part3.project.model.Admin;

import java.util.List;

public interface AdminRepository extends GenericRepository<Admin,Integer>  {
    Admin findByUserEmail(String email);
    void deleteByUserEmail(String email);
    List<Admin> findByUserFirstName(String firstName);
    List<Admin> findByUserLastName(String lastName);


}
