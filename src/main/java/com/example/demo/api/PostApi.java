package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostApi {

    @Autowired
    private PostService postService;

    // 새로운 게시글 생성 엔드포인트 (RESTful 방식)
    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestParam Long boardId, 
            @RequestParam String title,
            @RequestParam String content, 
            @RequestParam Long author) {

        Post post = postService.createPost(boardId, title, content, author);
        return ResponseEntity.ok(post);  // 생성된 게시글 반환
    }
}

