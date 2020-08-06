package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
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

    @GetMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "Here is the form to create a post";
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String insert() {
        return "Post has been created";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model){
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post postToEdit = postsDao.getOne(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postsDao.save(postToEdit);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

}
