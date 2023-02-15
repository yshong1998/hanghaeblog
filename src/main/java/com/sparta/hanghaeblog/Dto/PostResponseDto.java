package com.sparta.hanghaeblog.Dto;


import com.sparta.hanghaeblog.entitiy.Post;
import lombok.Getter;


import java.util.List;


@Getter
public class PostResponseDto {
    private final String title;
    private final String username;
    private final String contents;
    private final String uploadTime;
    private List<CommentResponseDto> commentList;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.uploadTime = post.getCreatedAt().toString();
        this.username = post.getUser().getUsername();
        this.commentList = post.getComment().stream().map(CommentResponseDto::new).toList();
    }
}
