package com.sparta.hanghaeblog.entitiy;


import com.sparta.hanghaeblog.Dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private Collection<Comment> comment;

    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
        user.userPosts.add(this);
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
