package com.sparta.hanghaeblog.Dto;


import com.sparta.hanghaeblog.entitiy.Post;
import lombok.Getter;


import java.time.LocalDateTime;
import java.util.List;


@Getter
public class PostResponseDto {
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime uploadTime;
    private final List<CommentResponseDto> commentList;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.uploadTime = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.commentList = post.getComment().stream().map(CommentResponseDto::new).sorted(CommentResponseDto::compareTo).toList();
    }
}
