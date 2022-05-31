package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.SpecialistRepository;
import com.example.ProjectFinalMaktab_part3.project.service.SpecialistService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistServiceImpl extends GenericServiceImpl<Specialist, Integer> implements SpecialistService {
    private Specialist specialist;
    private SpecialistRepository specialistRepository;

    public SpecialistServiceImpl(GenericRepository<Specialist, Integer> genericRepository
            , Specialist specialist, SpecialistRepository specialistRepository) {
        super(genericRepository);
        this.specialist = specialist;
        this.specialistRepository = specialistRepository;
    }

    @Override
    public Specialist findByUserEmail(String email) {
        return null;
    }

    @Override
    public void deleteByUserEmail(String email) {

    }

    @Override
    public Specialist findByUserFirstName(String firstName) {
        return null;
    }

    @Override
    public Specialist findByUserLastName(String lastName) {
        return null;
    }

    @Override
    public Specialist findSpecialistBySubTaskId(Integer subTaskId) {
        return null;
    }

    @Override
    public List<Specialist> findSpecialistBySkill(String skill) {
        return null;
    }

    @Override
    public List<Specialist> findByOrderByScore(Specialist specialist, Sort sort) {
        return null;
    }
}
