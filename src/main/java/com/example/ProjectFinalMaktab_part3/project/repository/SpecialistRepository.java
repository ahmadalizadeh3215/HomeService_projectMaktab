package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface SpecialistRepository extends GenericRepository<Specialist, Integer> {
    Specialist findByEmail(String email);
    void deleteByEmail(String email);
    Specialist findByFirstName(String firstName);
    Specialist findByLastName(String lastName);
    Specialist findSpecialistBySubTaskId(Integer subTaskId);
    List<Specialist> findSpecialistBySkill(String skill);
    List<Specialist> findByOrderByScore(Specialist specialist , Sort sort);

}
