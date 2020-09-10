package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.ImageRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ProfileController {

    private UserRepository users;
    private ImageRepository imagesDao;

    public ProfileController(UserRepository users, ImageRepository imagesDao) {
        this.users = users;
        this.imagesDao = imagesDao;
    }


    @GetMapping("/profile/{username}")
    public String viewProfile(@PathVariable String username, Model model){
        model.addAttribute("user", username);
        return "profile";
    }

    @GetMapping("/profile")
    public String viewProfile(){
        return "profile";
    }

    @PostMapping("/profile")
    public String viewLoginInfo(@RequestParam(name = "username") String username, @RequestParam(name="password") String password, Model model){
        ArrayList<String> ads = new ArrayList<String>();
        ads.add("Ad 1");
        ads.add("Ad 2");
        ads.add("Ad 3");

        boolean isLoggedIn = true;

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("ads", ads);
        model.addAttribute("isLoggedIn", isLoggedIn);

//        user is redirected to their profile, with this information available
        return "profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String editProfile(@PathVariable long id, Model model){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", users.getOne(sessionUser.getId()));
        return "users/edit-profile";
    }



}
