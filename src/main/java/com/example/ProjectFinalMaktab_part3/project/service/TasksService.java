package com.example.ProjectFinalMaktab_part3.project.service;


import com.example.ProjectFinalMaktab_part3.project.model.Tasks;

public interface TasksService extends GenericService<Tasks, Integer> {
    Tasks findByName(String name);

    void deleteByName(String name);
}
