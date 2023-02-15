package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entitiy.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
