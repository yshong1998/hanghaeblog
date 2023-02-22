package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.PostRequestDto;
import com.sparta.hanghaeblog.Dto.PostResponseDto;
import com.sparta.hanghaeblog.entitiy.*;
import com.sparta.hanghaeblog.exception.ErrorCode;
import com.sparta.hanghaeblog.exception.RestApiException;
import com.sparta.hanghaeblog.repository.PostRepository;

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


    //게시글 작성
    @Transactional
    public ResponseEntity<PostResponseDto> createPost(PostRequestDto requestDto, User user) {
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
    @Transactional(readOnly = true)
    public ResponseEntity<PostResponseDto> getPost(Long id) {
        Post post = getPostById(id);
        PostResponseDto responseDto = new PostResponseDto(post);
        return ResponseEntity.ok()
                .body(responseDto);
    }

    //게시글 수정
    @Transactional
    public ResponseEntity<PostResponseDto> update(Long id, PostRequestDto requestDto, User user) {
        Post post = getPostById(id);
        if (user.getRole().equals(UserRoleEnum.ADMIN) || user.getId().equals(post.getUser().getId())) {
            post.update(requestDto);
        } else {
            throw new RestApiException(ErrorCode.USER_UNMATCHED);
        }
        postRepository.flush();
        return ResponseEntity.ok()
                .body(new PostResponseDto(post));
    }

    //게시글 삭제
    @Transactional
    public ResponseEntity<Message> deletePost(Long id, User user) {
        Post post = getPostById(id);
        if (user.getRole().equals(UserRoleEnum.ADMIN) || user.getId().equals(post.getUser().getId())) {
            postRepository.deleteById(post.getId());
        } else {
            throw new RestApiException(ErrorCode.USER_UNMATCHED);
        }
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "delete success"));
    }

    private Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RestApiException(ErrorCode.POST_NOT_EXIST)
        );
    }
}
