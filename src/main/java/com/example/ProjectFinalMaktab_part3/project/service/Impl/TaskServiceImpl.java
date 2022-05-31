package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Tasks;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.TaskRepository;
import com.example.ProjectFinalMaktab_part3.project.service.TasksService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends GenericServiceImpl<Tasks, Integer> implements TasksService {
    private Tasks tasks;
    private TaskRepository taskRepository;

    public TaskServiceImpl(GenericRepository<Tasks, Integer> genericRepository
            , Tasks tasks, TaskRepository taskRepository) {
        super(genericRepository);
        this.tasks = tasks;
        this.taskRepository = taskRepository;
    }

    @Override
    public Tasks findByName(String name) {
        return null;
    }

    @Override
    public void deleteByName(String name) {

    }
}
