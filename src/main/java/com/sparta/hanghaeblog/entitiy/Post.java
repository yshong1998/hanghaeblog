package com.sparta.hanghaeblog.entitiy;


import com.sparta.hanghaeblog.Dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();


    public Post(PostRequestDto requestDto,User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.user = user;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
