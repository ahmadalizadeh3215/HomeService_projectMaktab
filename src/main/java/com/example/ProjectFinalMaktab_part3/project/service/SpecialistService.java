package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SpecialistService extends GenericService<Specialist, Integer> {
    Specialist findByEmail(String email);

    void deleteByEmail(String email);

    Specialist findByFirstName(String firstName);

    Specialist findByLastName(String lastName);

    Specialist findSpecialistBySubTaskId(Integer subTaskId);

    List<Specialist> findSpecialistBySkill(String skill);

    List<Specialist> findByOrderByScore(Specialist specialist, Sort sort);


}
