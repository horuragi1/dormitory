package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "events")
public class Event {
	
	@Id @GeneratedValue
	private Long id;
	
	private String title;
	private LocalDate start_date;
	private LocalDate end_date;
	private boolean allDay;
	private String color;
	private String textColor;
	
	@ManyToOne
	private User user;
	
	public Long CreateEvent(User user, LocalDate start, LocalDate end) {
		this.start_date = start;
		this.end_date = end;
		this.title = "외박";
		this.allDay = true;
		this.color = "#f9e6bb";
		this.textColor = "#ffffff";
		this.user = user;
		
		return this.id;
	}

}
