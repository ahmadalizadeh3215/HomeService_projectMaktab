package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.SubTask;



public interface SubTaskRepository extends GenericRepository<SubTask, Integer> {
SubTask findByName(String subTaskName);
void deleteAllById(Integer subTaskId);
void deleteBySpecialistId(Integer specialistId);
SubTask findBySpecialistId(Integer specialistId);


}
