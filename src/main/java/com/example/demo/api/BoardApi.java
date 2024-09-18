package com.example.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Post;
import com.example.demo.model.PostDTO;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardApi {

	private static final Logger logger = LoggerFactory.getLogger(BoardApi.class);
	
    @Autowired
    private BoardService boardService;

    @GetMapping("/{boardId}/posts")
    public ResponseEntity<Page<PostDTO>> getPostsByBoardId(
            @PathVariable("boardId") Long boardId,
            @RequestParam("page") int page) {

        Page<Post> posts = boardService.getPostsByBoardId(boardId, page);
        Page<PostDTO> postDTOs = posts.map(post -> {
            PostDTO dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTitle(post.getTitle());
            dto.setContent(post.getContent());
            dto.setAuthor(post.getAuthor()); // Assuming author is User
            dto.setCreatedAt(post.getCreatedAt().toString());
            dto.setUpdatedAt(post.getUpdatedAt().toString());
            return dto;
        });

        return ResponseEntity.ok(postDTOs);
    }

}

