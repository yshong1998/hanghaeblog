package com.sparta.hanghaeblog.Dto;

import com.sparta.hanghaeblog.entitiy.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private String title;
    private String writer;
    private String contents;
    private String uploadTime;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.contents = post.getContents();
        this.uploadTime = post.getCreatedAt().toString();
    }
}
