package com.example.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.SleepOverController;
import com.example.demo.model.Event;
import com.example.demo.model.EventDTO;
import com.example.demo.model.User;
import com.example.demo.service.EventService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GetDatesApi {
	
	private final EventService eventService;
	private final UserService userService;
	
	@GetMapping("/api/data")
	public List<EventDTO> getDates(HttpSession session) {
		String username = (String) session.getAttribute("username");
		
		User user = userService.findByUsername(username).get();
		
		Long id = user.getId();
		
		List<Event> events = eventService.findEventsByUserId(id);
		
		// Convert Event entities to EventDTOs
	    List<EventDTO> eventDTOs = events.stream().map(event -> {
	        EventDTO dto = new EventDTO();
	        dto.setId(event.getId());
	        dto.setTitle(event.getTitle());
	        dto.setStart(event.getStart_date()); // Map start_date to start
	        dto.setEnd(event.getEnd_date());     // Map end_date to end
	        dto.setAllDay(event.isAllDay());
	        dto.setColor(event.getColor());
	        dto.setTextColor(event.getTextColor());
	        return dto;
	    }).collect(Collectors.toList());
	    
	    return eventDTOs;
	}
	
	@GetMapping("/api/data-pages")
	public Page<EventDTO> getDates(
	        HttpSession session, 
	        @RequestParam(name = "page", defaultValue = "0") int page, 
	        @RequestParam(name = "size", defaultValue = "10") int size) {
		
		System.out.println("page is " + page);
		System.out.println("size is " + size);
	    
	    String username = (String) session.getAttribute("username");
	    User user = userService.findByUsername(username).get();
	    Long id = user.getId();

	    PageRequest pageRequest = PageRequest.of(page, size);
	    Page<Event> eventsPage = eventService.findEventsByUserId(id, pageRequest);

	    // Convert Event entities to EventDTOs
	    Page<EventDTO> eventDTOsPage = eventsPage.map(event -> {
	        EventDTO dto = new EventDTO();
	        dto.setId(event.getId());
	        dto.setTitle(event.getTitle());
	        dto.setStart(event.getStart_date()); // Map start_date to start
	        dto.setEnd(event.getEnd_date());     // Map end_date to end
	        dto.setAllDay(event.isAllDay());
	        dto.setColor(event.getColor());
	        dto.setTextColor(event.getTextColor());
	        return dto;
	    });

	    return eventDTOsPage;
	}

	
	
	
	

}
