package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String index() {
        return "Here are all the posts";
    }

    @GetMapping("/posts/{id")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "Here is a post with the id: " + id;
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
