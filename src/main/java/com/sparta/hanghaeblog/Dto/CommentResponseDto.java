package com.sparta.hanghaeblog.Dto;

import com.sparta.hanghaeblog.entitiy.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String contents;
    private String modifiedTime;
    private final String uploadTime;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.uploadTime = comment.getCreatedAt().toString();
        this.modifiedTime = comment.getModifiedAt().toString();
    }
}
