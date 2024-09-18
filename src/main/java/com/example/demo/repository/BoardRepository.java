package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Board;
import com.example.demo.model.Post;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
