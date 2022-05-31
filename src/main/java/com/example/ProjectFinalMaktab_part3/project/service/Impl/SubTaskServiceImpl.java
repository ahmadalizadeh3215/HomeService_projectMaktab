package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.SubTask;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.SubTaskRepository;
import com.example.ProjectFinalMaktab_part3.project.service.SubTaskService;
import org.springframework.stereotype.Service;

@Service
public class SubTaskServiceImpl extends GenericServiceImpl<SubTask, Integer> implements SubTaskService {
    private SubTask subTask;
    private SubTaskRepository subTaskRepository;

    public SubTaskServiceImpl(GenericRepository<SubTask, Integer> genericRepository
            , SubTask subTask, SubTaskRepository subTaskRepository) {
        super(genericRepository);
        this.subTask = subTask;
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    public SubTask findByName(String subTaskName) {
        return null;
    }

    @Override
    public void deleteAllById(Integer subTaskId) {

    }

    @Override
    public void deleteBySpecialistId(Integer specialistId) {

    }

    @Override
    public SubTask findBySpecialistId(Integer specialistId) {
        return null;
    }
}
