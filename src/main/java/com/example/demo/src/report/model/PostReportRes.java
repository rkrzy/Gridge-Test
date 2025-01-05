package com.example.demo.src.report.model;

import com.example.demo.src.report.entity.PostReport;
import com.example.demo.src.user.entity.User;

public class PostReportRes {

    private Long id;
    private Long postId;
    private User user;
    private String content;

    public PostReportRes(PostReport postReport)
    {
        this.id = postReport.getId();
        this.postId = postReport.getPost().getId();
        this.user = postReport.getUser();
        this.content = postReport.getContent();
    }

}
