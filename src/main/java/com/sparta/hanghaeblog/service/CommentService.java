package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.CommentRequestDto;
import com.sparta.hanghaeblog.Dto.CommentResponseDto;
import com.sparta.hanghaeblog.entitiy.*;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.CommentRepository;
import com.sparta.hanghaeblog.repository.PostRepository;
import com.sparta.hanghaeblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long postId, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);// HttpServletRequest에서 받아온 토큰을 secretkey로 디코드
        Claims claims = jwtUtil.getTokenClaims(token); // 디코드한 String으로부터 Claims를 받아온다.
        User user = findUserByTokenClaims(claims); // Claims 안에 있는 username으로 user를 찾아 저장한다. (없으면 예외 처리됨)
        Post post = findPostByPostId(postId); // pathvariable로 받아 왔던 게시글의 id를 통해 게시글 찾는다
        Comment comment = commentRepository.saveAndFlush(new Comment(requestDto, user, post)); // 코멘트를 생성하고 저장한다.
        System.out.println(comment.getPost().getTitle());
        return ResponseEntity.ok()
                .body(new CommentResponseDto(comment));
    }

    @Transactional
    public ResponseEntity<CommentResponseDto> updateComment(Long commentId, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getTokenClaims(token);
        User user = findUserByTokenClaims(claims);
        Comment comment = findCommentByCommentId(commentId); //여기까진 create와 동일
        // user가 관리자이거나 코멘트의 user와 토큰의 user가 일치하다면 update한다.
        if(user.getRole().equals(UserRoleEnum.ADMIN)||user.getRole().equals(comment.getUser().getRole())){
            comment.update(requestDto);
        }
        return ResponseEntity.ok()
                .body(new CommentResponseDto(comment));
    }

    @Transactional
    public ResponseEntity<Message> deleteComment(Long commentId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getTokenClaims(token);
        User user = findUserByTokenClaims(claims);
        Comment comment = findCommentByCommentId(commentId);
        if(user.getRole().equals(UserRoleEnum.ADMIN)||user.getRole().equals(comment.getUser().getRole())){
            commentRepository.delete(comment); //update와 삭제한다는 것만 다르다.
        }
        return ResponseEntity.ok()
                .body(new Message(200,"delete success"));
    }
    private Comment findCommentByCommentId(Long commentIndex) {
        return commentRepository.findById(commentIndex).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }

    private Post findPostByPostId(Long postIndex) {
        return postRepository.findById(postIndex).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

    private User findUserByTokenClaims(Claims claims) {
        return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
    }
}
