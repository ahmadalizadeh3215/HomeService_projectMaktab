package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Tasks;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SubTaskServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {
    private TaskServiceImpl taskService;
    private SubTaskServiceImpl subTaskService;

    public TaskController(TaskServiceImpl taskService,
                          SubTaskServiceImpl subTaskService) {
        this.taskService = taskService;
        this.subTaskService = subTaskService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Tasks> create(@RequestBody @Valid Tasks tasks) {
        Tasks tasks1=taskService.save(tasks);
        return ResponseEntity.ok(tasks1);
    }
}
