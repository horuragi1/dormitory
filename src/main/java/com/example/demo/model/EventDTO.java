package com.example.demo.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventDTO {
    private Long id;
    private String title;
    private LocalDate start;
    private LocalDate end;
    private boolean allDay;
    private String color;
    private String textColor;
    
    // Getters and setters
}

