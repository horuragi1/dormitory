package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventDTO {
    private Long id;
    private String title;
    private String start;
    private String end;
    private boolean allDay;
    private String color;
    private String textColor;
    
    // Getters and setters
}

