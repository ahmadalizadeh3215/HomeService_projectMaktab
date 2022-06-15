package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.model.*;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.CommentServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.CustomerServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SpecialistServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private SpecialistServiceImpl specialistService;
    private CustomerServiceImpl customerService;
    private CommentServiceImpl commentService;
    private Comment comment;

    public CommentController(SpecialistServiceImpl specialistService,
                             CustomerServiceImpl customerService,
                             CommentServiceImpl commentService,
                             Comment comment) {
        this.specialistService = specialistService;
        this.customerService = customerService;
        this.commentService = commentService;
        this.comment=comment;
    }

    @PostMapping(value = "/setComment/{specialistEmail}/{customerEmail}")
    public ResponseEntity<Comment> create(@RequestBody Comment comment,
                                          @PathVariable("specialistEmail") String specialistEmail,
                                          @PathVariable("customerEmail") String customerEmail) {
        System.out.println(specialistEmail);
        System.out.println(customerEmail);
        Specialist specialist = specialistService.findByEmail(specialistEmail);
        Customer customer = customerService.findByEmail(customerEmail);
        comment.setSpecialist(specialist);
        comment.setCustomer(customer);
        commentService.save(comment);
        return ResponseEntity.ok(comment);
    }


}
