package com.example.demo.src.reply.model;


import com.example.demo.src.image.entity.ProfileImage;
import com.example.demo.src.reply.entity.Reply;
import com.example.demo.src.user.model.UserInfoDTO;

import java.time.LocalDateTime;

public class ReplyDetailDTO {
    private Long replyId;
    private String content;
    private UserInfoDTO userInfoDTO;
    private LocalDateTime createdAt;

    public ReplyDetailDTO(Reply reply, ProfileImage profileImage){
        this.replyId = reply.getId();
        this.content = reply.getContent();
        this.userInfoDTO = new UserInfoDTO(reply.getUser(), profileImage);
        this.createdAt = reply.getCreatedAt();
    }
}
