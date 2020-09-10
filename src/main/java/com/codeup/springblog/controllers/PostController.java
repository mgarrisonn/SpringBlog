package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.CommentRepository;
import com.codeup.springblog.repositories.ImageRepository;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final CommentRepository commentDao;
    private final ImageRepository imagesDao;
//    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, CommentRepository commentDao, ImageRepository imagesDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.commentDao = commentDao;
        this.imagesDao = imagesDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> myPost = postsDao.findAll();
        model.addAttribute("posts", myPost);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable(value = "id") long id, Model model) {
        Post pulledPost = postsDao.getOne(id);
        List<Comment> getComments = postsDao.getOne(id).getComments();
        model.addAttribute("comments", getComments);
        model.addAttribute("post", pulledPost);
        return "posts/show";
    }

    // without form model binding
//    @GetMapping("/posts/create")
//    public String create() {
//        return "posts/create";
//    }

    // without form model binding

//    @PostMapping("/posts/create")
//    public String insert(@RequestParam String title, @RequestParam String body) {
//        User user = usersDao.getOne(1L);
//        Post post = new Post(title, body, user);
//        postsDao.save(post);
//        return "redirect:/posts";
//    }

    // without form model binding

//    @GetMapping("/posts/{id}/edit")
//    public String editForm(@PathVariable long id, Model model){
//        model.addAttribute("post", postsDao.getOne(id));
//        return "posts/edit";
//    }

    // without form model binding
//    @PostMapping("/posts/{id}/edit")
//    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body){
//        Post postToEdit = postsDao.getOne(id);
//        postToEdit.setTitle(title);
//        postToEdit.setBody(body);
//        postsDao.save(postToEdit);
//        return "redirect:/posts/" + id;
//    }

    @PostMapping("/posts/show/{id}/comment")
    public String getPost(@PathVariable(value = "id") long id, @RequestParam(name = "createComment") String createComment) {
        Post post = postsDao.getOne(id);
        Comment comment = new Comment();
        comment.setContent(createComment);
        comment.setParentPost(post);
        commentDao.save(comment);
        return "redirect:/posts/show/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable(name = "delete") long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post){
//        User user = usersDao.getOne(1L);
//        Post post = new Post();
//        post.setAuthor(user);
//        post.setTitle(createTitle);
//        post.setPost(createBody);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setAuthor(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id,
                            @RequestParam(name = "titleEdit")String titleUpdate,
                           @RequestParam(name = "postEdit") String postUpdate){
        Post post = postsDao.getOne(id);
        post.setTitle(titleUpdate);
        post.setPost(postUpdate);
        postsDao.save(post);
        return "redirect:/posts/" + id;
    }



//    @GetMapping("users/register")
//    public String showRegisterForm(Model model){
//        model.addAttribute("user", new User());
//
//        return "register";
//    }
//
//    @PostMapping("users/register")
//    public String createUser(@ModelAttribute User user){
//        usersDao.save(user);
//        return "redirect:/posts";
//    }
}
