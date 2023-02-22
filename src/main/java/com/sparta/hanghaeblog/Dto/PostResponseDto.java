package com.sparta.hanghaeblog.Dto;


import com.sparta.hanghaeblog.entitiy.Post;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class PostResponseDto {
    private final String title;
    private final String username;
    private final String contents;
    private final Integer like;
    private final LocalDateTime uploadTime;
    private final List<CommentResponseDto> commentList;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.uploadTime = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.commentList = post.getCommentList().stream().map(CommentResponseDto::new).sorted(CommentResponseDto::compareTo).toList();
        this.like = post.getPostLikeList().size();
    }
}
