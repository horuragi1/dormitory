package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // 댓글 생성
    @Transactional
    public Comment createComment(Long postId, String content, Long author) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    // 특정 게시글의 모든 댓글 조회(미구현)
    /*
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }*/

    // 댓글 수정
    @Transactional
    public Comment updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        commentRepository.delete(comment);
    }
}
