package com.example.demo.src.post.model;

import com.example.demo.src.image.model.ImageInfoDTO;
import com.example.demo.src.user.model.UserInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
    private Long postId;
    private UserInfoDTO userInfo;
    private String content;
    private List<ImageInfoDTO> imageList;
    private Long likeCount;
    private Long replyCount;
    private LocalDateTime createAt;

    public PostDTO(Long postId,
                   UserInfoDTO userInfo,
                   String content,
                   List<ImageInfoDTO> imageList,
                   Long likeCount,
                   Long replyCount,
                   LocalDateTime createAt) {
        this.postId = postId;
        this.userInfo = userInfo;
        this.content = content;
        this.imageList = imageList;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.createAt = createAt;
    }
}
