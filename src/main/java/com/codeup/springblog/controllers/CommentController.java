package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.CommentRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    private final CommentRepository commentDao;
    private final UserRepository userDao;
    private final PostRepository postDao;


    public CommentController(CommentRepository commentDao, UserRepository userDao, PostRepository postDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/comments")
    public String index (Model model) {
        List<Comment> myComment = commentDao.findAll();
        model.addAttribute("comment", myComment);
        return "comments/index";
    }

    @GetMapping("/comments/{id}")
    public String show(@PathVariable Long id, Model model) {
        Comment pulledComment = commentDao.getOne(id);
        model.addAttribute("comment", pulledComment);
        return "comments/show";
    }

    @GetMapping("/comments/create/{id")
    public String showCreateCommentForm(Model model, @PathVariable Long id) {
        User user = userDao.findByUsername(postDao.getOne(id));
        model.addAttribute("user", user);
        model.addAttribute("comment", new Comment());
        return "comments/create";
    }

    @PostMapping("comments/create")
    public String createComment(@ModelAttribute Comment comment, Model model, @RequestParam(name = "userId") Long userId, Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userC = userDao.findByUsername(userDao.getOne(userId));
        comment.setContent(userC.getUsername());
        comment.setParentPost(user.getId(id));
        return "redirect:/posts";
    }

}
