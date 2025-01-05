package com.example.demo.src.post;

import com.example.demo.src.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    Optional<Post> findById(Long postId);
}
