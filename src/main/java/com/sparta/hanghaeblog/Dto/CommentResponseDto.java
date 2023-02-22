package com.sparta.hanghaeblog.Dto;

import com.sparta.hanghaeblog.entitiy.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto implements Comparable<CommentResponseDto> {
    private String contents;
    private LocalDateTime uploadTime;
    private LocalDateTime modifiedTime;
    private Integer commentLike;


    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.uploadTime = comment.getCreatedAt();
        this.modifiedTime = comment.getModifiedAt();
        this.commentLike = comment.getCommentLikeList().size();
    }

    @Override
    public int compareTo(CommentResponseDto o) {
        if(this.getUploadTime().isBefore(o.getUploadTime())){
            return 1;
        } else if (this.getUploadTime().isEqual(o.getUploadTime())){
            return 0;
        }
        return -1;
    }
}
