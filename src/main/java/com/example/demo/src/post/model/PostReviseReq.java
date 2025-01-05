package com.example.demo.src.post.model;

import lombok.Getter;

import java.util.List;

@Getter
public class PostReviseReq {
    private String content;  // 수정된 글 내용
    private List<String> imageUrls;
}
