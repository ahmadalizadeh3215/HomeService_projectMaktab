package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.Tasks;


public interface TaskRepository extends GenericRepository<Tasks,Integer> {
Tasks findByName(String name);
void deleteByName(String name);
}
