package com.sparta.hanghaeblog.Dto;

import com.sparta.hanghaeblog.entitiy.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String contents;
    private String uploadTime;
    private String modifiedTime;


    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.uploadTime = comment.getCreatedAt().toString();
        this.modifiedTime = comment.getModifiedAt().toString();
    }
}
