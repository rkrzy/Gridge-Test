package com.example.demo.src.report.model;

import com.example.demo.src.report.entity.ReplyReport;
import com.example.demo.src.user.entity.User;

public class ReplyReportRes {

    private Long id;
    private Long replyId;
    private User user;
    private String content;

    public ReplyReportRes(ReplyReport ReplyReport){
        this.id = ReplyReport.getId();
        this.replyId = ReplyReport.getReply().getId();
        this.user = ReplyReport.getUser();
        this.content = ReplyReport.getContent();
    }
}
