package com.sparta.hanghaeblog.controller;


import com.sparta.hanghaeblog.Dto.PostRequestDto;
import com.sparta.hanghaeblog.Dto.PostResponseDto;
import com.sparta.hanghaeblog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 작성
    @PostMapping("api/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    //게시글 전체 조회
    @GetMapping("api/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }
    //선택한 게시글 조회
    @GetMapping("api/posts/{id}")
    public PostResponseDto getClickedPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //게시글 수정
    @PutMapping("api/posts/{id}")
    public PostResponseDto updatePost(@RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.update(requestDto, request);
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.deletePost(id,requestDto);
    }
}
