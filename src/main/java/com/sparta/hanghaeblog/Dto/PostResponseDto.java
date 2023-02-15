package com.sparta.hanghaeblog.Dto;

import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.entitiy.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private final String title;
    private final String contents;
    private final String uploadTime;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.uploadTime = post.getCreatedAt().toString();
    }
}
