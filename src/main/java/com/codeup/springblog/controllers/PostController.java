package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;

    public PostController(PostRepository postsDao, UserRepository usersDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts")
//    @ResponseBody
    public String index(Model model) {
//        ArrayList<Post> myPost = new ArrayList<>();
//        myPost.add(new Post(2, "Title 2", "Text here"));
//        myPost.add(new Post(3, "Title 3", "Text here"));
//        myPost.add(new Post(4, "Title 4", "Text here"));

//        return "Here are all the posts";
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String show(@PathVariable long id, Model model) {
        Post pulledPost = postsDao.getOne(id);
        model.addAttribute("post", pulledPost);
//        Post myPost = new Post(id, "Title", "Hello world");
//        return "Here is a post with the id: " + id;
//        model.addAttribute("title", myPost.getTitle());
//        model.addAttribute("body", myPost.getBody());
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

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
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
        User user = usersDao.getOne(1L);
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
    public String editPost(@PathVariable long id, Post post){
        User user = usersDao.getOne(1L);
        post.setAuthor(user);
        postsDao.save(post);
        return "redirect:/posts";
    }

}
