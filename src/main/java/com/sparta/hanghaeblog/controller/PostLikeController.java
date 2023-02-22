package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.security.UserDetailsImpl;
import com.sparta.hanghaeblog.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postlike")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Message> likeThisPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postLikeService.switchLikeOrNot(postId, userDetails.getUser());
        return ResponseEntity.ok()
                .body(new Message(HttpStatus.OK.value(), "like success"));
    }
}
