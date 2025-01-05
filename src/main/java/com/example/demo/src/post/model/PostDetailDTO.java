package com.example.demo.src.post.model;

import com.example.demo.src.image.model.ImageInfoDTO;
import com.example.demo.src.reply.model.ReplyDetailDTO;
import com.example.demo.src.user.model.UserInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetailDTO {
    private Long postId;
    private UserInfoDTO userInfo;
    private String content;
    private List<ImageInfoDTO> imageList;
    private Long likeCount;
    private List<ReplyDetailDTO> replyList;
    private LocalDateTime createAt;

    public PostDetailDTO(Long postId,
                   UserInfoDTO userInfo,
                   String content,
                   List<ImageInfoDTO> imageList,
                   Long likeCount,
                   List<ReplyDetailDTO> replyList,
                   LocalDateTime createAt) {
        this.postId = postId;
        this.userInfo = userInfo;
        this.content = content;
        this.imageList = imageList;
        this.likeCount = likeCount;
        this.replyList = replyList;
        this.createAt = createAt;
    }

}
