package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Admin;
import com.example.ProjectFinalMaktab_part3.project.repository.AdminRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends GenericServiceImpl<Admin, Integer> implements AdminService {
    private Admin admin;
    private AdminRepository adminRepository;

    public AdminServiceImpl(GenericRepository<Admin, Integer> genericRepository
            , Admin admin, AdminRepository adminRepository) {
        super(genericRepository);
        this.admin = admin;
        this.adminRepository = adminRepository;
    }


    @Override
    public Admin findByUserEmail(String email) {
        return null;
    }

    @Override
    public void deleteByUserEmail(String email) {

    }

    @Override
    public List<Admin> findByUserFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Admin> findByUserLastName(String lastName) {
        return null;
    }
}
