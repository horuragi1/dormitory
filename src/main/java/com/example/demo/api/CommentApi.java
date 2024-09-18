package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentApi {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestParam Long postId,
            @RequestParam String content,
            @RequestParam Long author) {

        Comment comment = commentService.createComment(postId, content, author);
        return ResponseEntity.ok(comment);
    }

    // 특정 게시글의 모든 댓글 조회(미구현)
    /*
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }*/

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestParam String content) {

        Comment updatedComment = commentService.updateComment(id, content);
        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
