package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventWithPeople {
    private String title;
    private LocalDate start_date;
    private LocalDate end_date;
    private String people;

    // Getters and Setters
}
