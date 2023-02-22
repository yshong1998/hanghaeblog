package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.CommentRequestDto;
import com.sparta.hanghaeblog.Dto.CommentResponseDto;
import com.sparta.hanghaeblog.entitiy.*;
import com.sparta.hanghaeblog.exception.ErrorCode;
import com.sparta.hanghaeblog.exception.RestApiException;
import com.sparta.hanghaeblog.repository.CommentRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = findPostByPostId(postId); // pathvariable로 받아 왔던 게시글의 id를 통해 게시글 찾는다
        Comment comment = commentRepository.save(new Comment(requestDto, user, post)); // 코멘트를 생성하고 저장한다.
        return ResponseEntity.ok()
                .body(new CommentResponseDto(comment));
    }

    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = findCommentByCommentId(commentId); //여기까진 create와 동일
        // user가 관리자이거나 코멘트의 user와 토큰의 user가 일치하다면 update한다.
        if(user.getRole().equals(UserRoleEnum.ADMIN)||user.getRole().equals(comment.getUser().getRole())){
            comment.update(requestDto);
        } else {
            throw new RestApiException(ErrorCode.USER_UNMATCHED);
        }
        commentRepository.flush();
        return ResponseEntity.ok()
                .body(new CommentResponseDto(comment));
    }

    @Transactional
    public ResponseEntity<Message> deleteComment(Long commentId, User user) {
        Comment comment = findCommentByCommentId(commentId);
        if(user.getRole().equals(UserRoleEnum.ADMIN)||user.getRole().equals(comment.getUser().getRole())){
            commentRepository.delete(comment); //update와 삭제한다는 것만 다르다.
        }
        return ResponseEntity.ok()
                .body(new Message(200,"delete success"));
    }
    private Comment findCommentByCommentId(Long commentIndex) {
        return commentRepository.findById(commentIndex).orElseThrow(
                () -> new RestApiException(ErrorCode.COMMENT_NOT_EXIST)
        );
    }

    private Post findPostByPostId(Long postIndex) {
        return postRepository.findById(postIndex).orElseThrow(
                () -> new RestApiException(ErrorCode.POST_NOT_EXIST)
        );
    }

}
