package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	// 게시판 ID로 게시글을 검색
    Page<Post> findByBoard_Id(Long boardId, Pageable pageable);
	
}
