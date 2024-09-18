package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")  // TEXT로 명시적으로 지정
    private String content;

    @Column(nullable = false)
    private Long author;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)  // N:1 관계 설정
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();  // 게시글 생성 시간 설정
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();  // 게시글 수정 시간 설정
    }
}
