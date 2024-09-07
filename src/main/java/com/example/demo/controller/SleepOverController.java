package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Event;
import com.example.demo.model.EventDTO;
import com.example.demo.model.User;
import com.example.demo.service.EventService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SleepOverController {
	
	private final EventService eventService;
	private final UserService userService;
	
	@GetMapping("/view")
	public String view() {
		
		return "view";
		
	}
	
	@GetMapping("/apply")
	public String apply() {
		
		return "apply";
		
	}
	
	@PostMapping("/submitDateRange")
	public String apply_date(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate, HttpSession session) {
		
		Event event = new Event();
		
		String username = (String) session.getAttribute("username");
		
		User user = userService.findByUsername(username).get();
		
		event.CreateEvent(user, startDate, endDate);
		
		eventService.saveEvent(event);
		
		return "view";
	}
	
	@GetMapping("/edit")
	public String edit() {
		
		return "edit";
		
	}

}
