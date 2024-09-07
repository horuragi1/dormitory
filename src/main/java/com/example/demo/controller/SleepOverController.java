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
	public String apply_date(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, HttpSession session) {
		
		Event event = new Event();
		
		String username = (String) session.getAttribute("username");
		
		User user = userService.findByUsername(username).get();
		
		// 날짜 형식을 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
     // 날짜 문자열을 LocalDate로 파싱
        LocalDate date = LocalDate.parse(endDate, formatter);
        
        // 하루 뒤 날짜 계산
        LocalDate nextDay = date.plusDays(0);
        
        // 결과를 문자열로 포맷
        String nextDayString = nextDay.format(formatter);
       
		
		event.CreateEvent(user, startDate, nextDayString);
		
		eventService.saveEvent(event);
		
		return "view";
	}
	
	@GetMapping("/edit")
	public String edit() {
		
		return "edit";
		
	}

}
