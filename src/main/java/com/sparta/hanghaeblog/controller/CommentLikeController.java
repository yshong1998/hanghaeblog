package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.security.UserDetailsImpl;
import com.sparta.hanghaeblog.service.CommentLikeService;
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
@RequestMapping("/api/commentlike")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentnumber}")
    public ResponseEntity<Message> likeThisComment(@PathVariable Long commentnumber, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentLikeService.switchLikeOrNot(commentnumber, userDetails.getUser());
        return ResponseEntity.ok(new Message(HttpStatus.OK.value(), "comment like success"));
    }
}
