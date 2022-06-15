package com.example.ProjectFinalMaktab_part3.project.service;


import com.example.ProjectFinalMaktab_part3.project.model.SubTask;
import com.example.ProjectFinalMaktab_part3.project.model.Tasks;

public interface SubTaskService extends GenericService<SubTask, Integer> {
    SubTask findByName(String subTaskName);

    void deleteAllById(Integer subTaskId);

    void deleteBySpecialistId(Integer specialistId);

    SubTask findBySpecialistId(Integer specialistId);

}
