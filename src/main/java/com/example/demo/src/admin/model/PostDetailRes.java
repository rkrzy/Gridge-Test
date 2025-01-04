package com.example.demo.src.admin.model;

import com.example.demo.common.entity.BaseEntity;
import com.example.demo.src.post.entity.Post;
import com.example.demo.src.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailRes {
    private Long id;
    private String content;
    private User user;
    private BaseEntity.State state;
    private LocalDateTime createdTime;

    public PostDetailRes(Post post)
    {
        this.id = post.getId();
        this.content = post.getContent();
        this.user = post.getUser();
        this.createdTime = post.getCreatedAt();
        this.state = post.getState();
    }
}
