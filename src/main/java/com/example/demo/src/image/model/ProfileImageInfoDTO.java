package com.example.demo.src.image.model;

import com.example.demo.src.image.entity.Image;
import com.example.demo.src.image.entity.ProfileImage;
import com.example.demo.src.user.entity.User;

public class ProfileImageInfoDTO {
    Long id;
    String imageURL;
    Long postId;

    public ProfileImageInfoDTO(ProfileImage profileImage)
    {
        this.id = profileImage.getId();
        this.imageURL = profileImage.getImageUrl();
        this.postId = profileImage.getUser().getId();
    }
}
