package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.PostRequestDto;
import com.sparta.hanghaeblog.Dto.PostResponseDto;
import com.sparta.hanghaeblog.entitiy.*;
import com.sparta.hanghaeblog.jwt.JwtUtil;
import com.sparta.hanghaeblog.repository.PostRepository;

import com.sparta.hanghaeblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


    //게시글 작성
    @Transactional
    public ResponseEntity<PostResponseDto> createPost(PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getTokenClaims(token);
        User user = findUserByTokenClaims(claims);
        Post post = postRepository.saveAndFlush(new Post(requestDto,user));
        return ResponseEntity.ok()
                .body(new PostResponseDto(post));
    }

    //전체 게시글 조회
    @Transactional(readOnly = true)
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : postList) {
            responseDtoList.add(new PostResponseDto(post));
        }
        return ResponseEntity.ok()
                .body(responseDtoList);
    }

    //선택 게시글 조회
    @Transactional
    public ResponseEntity<PostResponseDto> getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
        return ResponseEntity.ok()
                .body(new PostResponseDto(post));
    }

    //게시글 수정
    @Transactional
    public ResponseEntity<PostResponseDto> update(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getTokenClaims(token);
        User user = findUserByTokenClaims(claims);
        Post post = getPostById(id);
        if (user.getRole().equals(UserRoleEnum.ADMIN) || user.getId().equals(post.getUser().getId())) {
            post.update(requestDto);
        }
        return ResponseEntity.ok()
                .body(new PostResponseDto(post));
    }

    //게시글 삭제
    @Transactional
    public ResponseEntity<Message> deletePost(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getTokenClaims(token);
        User user = findUserByTokenClaims(claims);
        Post post = getPostById(id);
        if (user.getRole().equals(UserRoleEnum.ADMIN) || user.getId().equals(post.getUser().getId())) {
            postRepository.deleteById(post.getId());
        }
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "delete success"));
    }

    private User findUserByTokenClaims(Claims claims) {
        return userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
    }

    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
}
