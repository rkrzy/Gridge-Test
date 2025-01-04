package com.example.demo.src.admin.model;


import com.example.demo.src.post.entity.Post;
import com.example.demo.src.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.example.demo.common.entity.BaseEntity.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminPostRes {

    private Long id;
    private String content;
    private User user;
    private State state;
    private LocalDateTime createdTime;

    public AdminPostRes(Post post)
    {
        this.id = post.getId();
        this.content = post.getContent();
        this.user = post.getUser();
        this.createdTime = post.getCreatedAt();
        this.state = post.getState();
    }


}
