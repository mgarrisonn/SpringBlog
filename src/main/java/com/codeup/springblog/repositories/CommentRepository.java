package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Comment;
import com.codeup.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
