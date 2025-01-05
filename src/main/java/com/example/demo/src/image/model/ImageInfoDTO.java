package com.example.demo.src.image.model;

import com.example.demo.src.image.entity.Image;

public class ImageInfoDTO {
    Long id;
    String imageURL;
    Long postId;

    public ImageInfoDTO(Image image)
    {
        this.id = image.getId();
        this.imageURL = image.getImageUrl();
        this.postId = image.getPost().getId();
    }
}
