package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entitiy.Comment;
import com.sparta.hanghaeblog.entitiy.CommentLike;
import com.sparta.hanghaeblog.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByCommentAndUser(Comment comment, User user);
}
