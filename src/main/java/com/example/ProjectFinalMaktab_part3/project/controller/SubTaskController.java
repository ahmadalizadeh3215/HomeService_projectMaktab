package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.SubTask;
import com.example.ProjectFinalMaktab_part3.project.model.Tasks;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SubTaskServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/subtask")
public class SubTaskController {

    private SubTaskServiceImpl subTaskService;
    private Tasks tasks;
    private TaskServiceImpl taskService;

    public SubTaskController(SubTaskServiceImpl subTaskService,
                             Tasks tasks,
                             TaskServiceImpl taskService) {
        this.subTaskService = subTaskService;
        this.tasks = tasks;
        this.taskService = taskService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<SubTask> create(@RequestBody @Valid SubTask subTask) {
       SubTask subTask1=subTaskService.save(subTask);
        return ResponseEntity.ok(subTask1);
    }
}
