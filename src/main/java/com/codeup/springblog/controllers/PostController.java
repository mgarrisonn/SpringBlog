package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
//    @ResponseBody
    public String index(Model model) {
        ArrayList<Post> myPost = new ArrayList<>();
        myPost.add(new Post(2, "Title 2", "Text here"));
        myPost.add(new Post(3, "Title 3", "Text here"));
        myPost.add(new Post(4, "Title 4", "Text here"));

//        return "Here are all the posts";
        model.addAttribute("posts", myPost);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
//    @ResponseBody
    public String show(@PathVariable long id, Model model) {
        Post myPost = new Post(id, "Title", "Hello world");
//        return "Here is a post with the id: " + id;
        model.addAttribute("title", myPost.getTitle());
        model.addAttribute("body", myPost.getBody());
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

}
