package com.codeup.springblog.services;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SillySvc {

    private PostRepository postDao;
    private UserRepository userDao;

    public SillySvc(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public int totalPostCharacters(){
        Iterable<Post> posts = postDao.findAll();
        Iterable<User> users = userDao.findAll();

        int total = 0;
        for(Post post : posts){
            total += post.getPost().length();
        }

        for(User user : users){
            total += user.getUsername().length();
        }

        return total;
    }

}
