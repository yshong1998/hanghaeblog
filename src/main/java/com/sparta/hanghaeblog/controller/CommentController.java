package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.Dto.CommentRequestDto;
import com.sparta.hanghaeblog.Dto.CommentResponseDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.security.UserDetailsImpl;
import com.sparta.hanghaeblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
public class CommentController {

    private final CommentService commentService;

    //create
    @PostMapping("/postnumber/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }

    //update
    @PutMapping("/commentnumber/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }
    //delete
    @DeleteMapping("/commentnumber/{commentId}")
    public ResponseEntity<Message> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId, userDetails.getUser());
    }
}
