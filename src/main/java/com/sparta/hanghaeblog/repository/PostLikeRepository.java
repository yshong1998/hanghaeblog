package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entitiy.Post;
import com.sparta.hanghaeblog.entitiy.PostLike;
import com.sparta.hanghaeblog.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndUser(Post post, User user);
}
