package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Long author;
    private String createdAt;
    private String updatedAt;
    // Getter와 Setter...
}
