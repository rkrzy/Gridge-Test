package com.example.demo.src.user.model;


import com.example.demo.common.entity.BaseEntity.State;

public class UserInfoDTO {
    private Long id;
    private String name;
    private State state;

    public UserInfoDTO(Long id, String name, State state)
    {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
