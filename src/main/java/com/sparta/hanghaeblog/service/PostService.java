package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.PostRequestDto;
import com.sparta.hanghaeblog.Dto.PostResponseDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.entitiy.Post;
import com.sparta.hanghaeblog.entitiy.User;
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
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.saveAndFlush(new Post(requestDto, user));
            Message message = new Message(HttpStatus.OK.value(),"success");
            PostResponseDto responseDto = new PostResponseDto(post,message);
            return ResponseEntity.ok()
                    .body(responseDto);
        }
        return null;
    }


    //전체 게시글 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc(); //이건 필요 없긴 하다 저 아래에서 2개 이상이 찾아지면 알아서 컬렉션 List로 반환하기 때문
//         return postRepository.findAll(); // 이러면 그냥 postRepository 안의 모든 메모 불러오기고, 시간 내림차순으로 불러오게 해 보자 이건 postRepository에서 해 주면 된다.
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : postList){
            responseDtoList.add(new PostResponseDto(post));
        }
        return responseDtoList;
    }

    //선택 게시글 조회
    @Transactional
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물입니다.")
        );
        return new PostResponseDto(post);
    }

    //게시글 수정
    @Transactional
    public PostResponseDto update(PostRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findById(user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
            );
            post.update(requestDto);
            return new PostResponseDto(post);
        }
        return null;
    }


    //게시글 삭제
    @Transactional
    public ResponseEntity<Message> deletePost(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findById(user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
            );
            postRepository.deleteById(post.getId());
            return ResponseEntity.ok()
                    .body(new Message(HttpStatus.OK.value(),"delete success"));
        }
        return null;
    }
}
