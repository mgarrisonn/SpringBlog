package com.codeup.springblog.controllers;

//import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
//import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RelationshipController {

    private final PostRepository postsDao;

    public RelationshipController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    // view all ads and comments as JSON
    @GetMapping("/rel/posts")
    @ResponseBody
    public List<Post> returnAllPost(){
        List<Post> posts = postsDao.findAll();
        return postsDao.findAll();
    }

    @GetMapping("/rel/posts/view")
    public String returnPostsView(Model model){
        model.addAttribute("posts", postsDao.findAll());
        return "rel/posts";
    }

}
