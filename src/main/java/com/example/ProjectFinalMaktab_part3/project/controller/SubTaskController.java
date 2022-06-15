package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.SubTask;
import com.example.ProjectFinalMaktab_part3.project.model.Tasks;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SpecialistServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SubTaskServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/subtask")
public class SubTaskController {

    private SubTaskServiceImpl subTaskService;
    private Tasks tasks;
    private TaskServiceImpl taskService;
    private Specialist specialist;
    private SpecialistServiceImpl specialistService;

    public SubTaskController(SubTaskServiceImpl subTaskService,
                             Tasks tasks,
                             TaskServiceImpl taskService,
                             Specialist specialist,
                             SpecialistServiceImpl specialistService) {
        this.subTaskService = subTaskService;
        this.tasks = tasks;
        this.taskService = taskService;
        this.specialist=specialist;
        this.specialistService=specialistService;

    }

    @PostMapping(value = "/save/{id}/{email}")
    public ResponseEntity<SubTask> create( @RequestBody SubTask subTask,
                                           @PathVariable("id") Integer id,
                                           @PathVariable("email") String email) {
        Tasks tasks1 = taskService.findById(id).orElseThrow(()
                -> new IllegalStateException("tasks not found ! "));
       Specialist specialist1=specialistService.findByEmail(email);
        subTask.setTasks(tasks1);
        subTask.setSpecialist(specialist1);
        subTaskService.save(subTask);
        return ResponseEntity.ok(subTask);
    }

}
