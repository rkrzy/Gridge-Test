package com.example.demo.src.user.model;


import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.user.entity.User;

public class UserInfoDTO {
    private Long id;
    private String name;
    private State state;

    public UserInfoDTO(User user)
    {
        this.id = user.getId();
        this.name = user.getName();
        this.state = user.getState();
    }
}
