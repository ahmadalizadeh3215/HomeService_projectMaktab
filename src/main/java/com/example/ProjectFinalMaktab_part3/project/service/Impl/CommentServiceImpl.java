package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Comment;
import com.example.ProjectFinalMaktab_part3.project.repository.CommentRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment, Integer> implements CommentService {
    private Comment comment;
    private CommentRepository commentRepository;

    public CommentServiceImpl(GenericRepository<Comment, Integer> genericRepository
            , Comment comment, CommentRepository commentRepository) {
        super(genericRepository);
        this.comment = comment;
        this.commentRepository = commentRepository;
    }
}
