package com.sparta.hanghaeblog.repository;

import com.sparta.hanghaeblog.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository를 상속 받아 데이터 베이스와 연결
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc(); // 모두 찾기 + 뒤의 내용을 기준으로 정렬해 줘 + ModifiedAt이라는 멤버 변수를 + 내림차순으로();


}
