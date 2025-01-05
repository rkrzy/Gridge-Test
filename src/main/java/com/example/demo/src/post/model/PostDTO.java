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
    private Long LikeCount;
    private Long ReplyCount;
    private LocalDateTime createAt;

}
