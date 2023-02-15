package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.Dto.CommentRequestDto;
import com.sparta.hanghaeblog.Dto.CommentResponseDto;
import com.sparta.hanghaeblog.entitiy.Message;
import com.sparta.hanghaeblog.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
public class CommentController {

    private final CommentService commentService;

    //create
    @PostMapping("/postnumber/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.createComment(postId, commentRequestDto, request);
    }

    //update
    @PutMapping("/commentnumber/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request){
        return commentService.updateComment(commentId, commentRequestDto, request);
    }
    //delete
    @DeleteMapping("/commentnumber/{commentId}")
    public ResponseEntity<Message> deleteComment(@PathVariable Long commentId, HttpServletRequest request){
        return commentService.deleteComment(commentId, request);
    }
}
