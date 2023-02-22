package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.entitiy.Post;
import com.sparta.hanghaeblog.entitiy.PostLike;
import com.sparta.hanghaeblog.entitiy.User;
import com.sparta.hanghaeblog.repository.PostLikeRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void switchLikeOrNot(Long postId, User user) {
        Post post = getPostById(postId);
        Optional<PostLike> postLike = postLikeRepository.findByPostAndUser(post, user);
        if(postLike.isEmpty()){
            postLikeRepository.save(new PostLike(post, user));
        } else {
            postLikeRepository.delete(postLike.get());
            postLikeRepository.flush();
        }
    }

    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
}
