package com.sparta.hanghaeblog.service;

import com.sparta.hanghaeblog.Dto.PostRequestDto;
import com.sparta.hanghaeblog.Dto.PostResponseDto;
import com.sparta.hanghaeblog.entitiy.Post;
import com.sparta.hanghaeblog.entitiy.Timestamped;
import com.sparta.hanghaeblog.repository.PostRepository;

import lombok.RequiredArgsConstructor;
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
    public Post createPost(PostRequestDto requestDtd) {
        Post post = new Post(requestDtd);
        postRepository.save(post);
        return post;
    }


    //전체 게시글 조회
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        List<Post> postList = new ArrayList<>(); //이건 필요 없긴 하다 저 아래에서 2개 이상이 찾아지면 알아서 컬렉션 List로 반환하기 때문
//         return postRepository.findAll(); // 이러면 그냥 postRepository 안의 모든 메모 불러오기고, 시간 내림차순으로 불러오게 해 보자 이건 postRepository에서 해 주면 된다.
        return postRepository.findAllByOrderByModifiedAtDesc();
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
    public String update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if(requestDto.getPassword().equals(post.getPassword())){
            post.update(requestDto);
            return "게시글이 수정되었습니다.";
        }
        return "비밀번호가 일치하지 않습니다.";
    }


    //게시글 삭제
    @Transactional
    public String deletePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if(requestDto.getPassword().equals(post.getPassword())){
            postRepository.deleteById(id);
            return "게시글이 삭제되었습니다.";
        }
        return "비밀번호가 일치하지 않습니다.";
    }
}
