package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Comment;
import com.codeup.springblog.repositories.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommentController {

    private final CommentRepository commentDao;


    public CommentController(CommentRepository commentDao) {
        this.commentDao = commentDao;
    }

    @GetMapping("/comments")
    public String index (Model model) {
        List<Comment> myComment = commentDao.findAll();
        model.addAttribute("comment", myComment);
        return "comments/index";
    }



}
