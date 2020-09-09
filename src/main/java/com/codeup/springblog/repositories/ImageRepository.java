package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {


    Image findByUserIdAndPostIsNull(Long id);

}
