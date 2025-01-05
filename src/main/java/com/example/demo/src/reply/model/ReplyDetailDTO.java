package com.example.demo.src.reply.model;


import com.example.demo.src.user.model.UserInfoDTO;

public class ReplyDetailDTO {
    private Long replyId;
    private String content;
    private UserInfoDTO userInfoDTO;

    ReplyDetailDTO(Long replyId, String content, UserInfoDTO userInfoDTO){
        this.replyId = replyId;
        this.content = content;
        this.userInfoDTO = userInfoDTO;
    }

}
