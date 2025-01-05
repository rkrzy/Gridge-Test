package com.example.demo.src.report.model;

import com.example.demo.src.report.entity.ReplyReport;
import com.example.demo.src.user.entity.User;

public class ReplyReportRes {

    private Long id;
    private Long replyId;
    private User user;
    private String content;

    public ReplyReportRes(ReplyReport commentReport){
        this.id = commentReport.getId();
        this.replyId = commentReport.getComment().getId();
        this.user = commentReport.getUser();
        this.content = commentReport.getContent();
    }
}
