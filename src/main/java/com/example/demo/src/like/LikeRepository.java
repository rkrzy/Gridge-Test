package com.example.demo.src.like;

import com.example.demo.src.like.entity.Like;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findAllByPostId(Long postId);
    Like findByPostIdAndUserId(Long postId, Long userId);
}
