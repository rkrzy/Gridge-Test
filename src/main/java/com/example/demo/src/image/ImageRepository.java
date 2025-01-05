package com.example.demo.src.image;

import com.example.demo.src.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPostId(Long id);

}
