package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.entitiy.Comment;
import com.sparta.hanghaeblog.entitiy.CommentLike;
import com.sparta.hanghaeblog.entitiy.User;
import com.sparta.hanghaeblog.exception.ErrorCode;
import com.sparta.hanghaeblog.exception.RestApiException;
import com.sparta.hanghaeblog.repository.CommentLikeRepository;
import com.sparta.hanghaeblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    public void switchLikeOrNot(Long commentnumber, User user) {
        Comment comment = commentRepository.findById(commentnumber).orElseThrow(
                () -> new RestApiException(ErrorCode.COMMENT_NOT_EXIST)
        );
        Optional<CommentLike> found = commentLikeRepository.findByCommentAndUser(comment, user);
        if(found.isEmpty()){
            commentLikeRepository.save(new CommentLike(comment, user));
        } else {
            commentLikeRepository.delete(found.get());
            commentLikeRepository.flush();
        }
    }
}
