package com.example.demo.src.image.model;

public class ImageInfoDTO {
    Long id;
    String imageURL;
    Long postId;

    public ImageInfoDTO(Long id, String imageURL, Long postId)
    {
        this.id = id;
        this.imageURL = imageURL;
        this.postId = postId;
    }
}
