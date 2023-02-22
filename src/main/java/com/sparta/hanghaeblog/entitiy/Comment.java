package com.sparta.hanghaeblog.entitiy;

import com.sparta.hanghaeblog.Dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity(name = "comment")
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long commentLike;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @OneToMany(mappedBy = "comment")
    private Collection<CommentLike> commentLikeList = new ArrayList<>();

    public Comment (CommentRequestDto requestDto, User user, Post post){
        this.contents = requestDto.getContents();
        this.user = user;
        this.post = post;
        this.commentLike = 0L;
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
