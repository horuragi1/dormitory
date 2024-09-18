package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Board;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public Post createPost(Long boardId, String title, String content, Long author) {
        // 게시판 조회 (게시글이 어느 게시판에 속할지 결정)
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        // 새로운 게시글 생성
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setBoard(board); // 게시판 설정

        // 게시판에 게시글 추가
        board.addPost(post);

        // 게시글 저장
        return postRepository.save(post);
    }
}
