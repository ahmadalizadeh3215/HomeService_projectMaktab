package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SpecialistService extends GenericService<Specialist, Integer> {
    Specialist findByUserEmail(String email);

    void deleteByUserEmail(String email);

    Specialist findByUserFirstName(String firstName);

    Specialist findByUserLastName(String lastName);

    Specialist findSpecialistBySubTaskId(Integer subTaskId);

    List<Specialist> findSpecialistBySkill(String skill);

    List<Specialist> findByOrderByScore(Specialist specialist, Sort sort);

}
