package com.example.demo.src.user.model;


import com.example.demo.common.entity.BaseEntity.State;
import com.example.demo.src.image.entity.ProfileImage;
import com.example.demo.src.user.entity.User;

public class UserInfoDTO {
    private Long id;
    private String name;
    private State state;
    private String profileUrl;
    public UserInfoDTO(User user, ProfileImage profileImage)
    {
        this.id = user.getId();
        this.name = user.getName();
        this.state = user.getState();
        this.profileUrl = profileImage.getImageUrl();
    }
}
