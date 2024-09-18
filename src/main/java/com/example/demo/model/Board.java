package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // 게시판 이름

    @Column(columnDefinition = "TEXT")  // TEXT로 명시적으로 지정
    private String description;  // 게시판 설명

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();  // 게시판에 속한 게시글들

    // 게시글 추가 메소드
    public void addPost(Post post) {
        posts.add(post);
        post.setBoard(this);
    }

    // 게시글 제거 메소드
    public void removePost(Post post) {
        posts.remove(post);
        post.setBoard(null);
    }
}

