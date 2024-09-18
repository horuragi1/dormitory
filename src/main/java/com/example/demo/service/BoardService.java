package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@Service
public class BoardService {

    @Autowired
    private PostRepository postRepository;

    // 게시판의 게시글을 페이징하여 조회하는 메소드
    public Page<Post> getPostsByBoardId(Long boardId, int page) {
        // 페이지당 10개씩 가져오기 위한 PageRequest 설정
        PageRequest pageable = PageRequest.of(page, 10);
        return postRepository.findByBoard_Id(boardId, pageable);
    }
}

