package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
	
	private final EventRepository eventRepository;
	
	@Transactional
	public Long saveEvent(Event event) {
		
		eventRepository.save(event);
		
		return event.getId();
		
	}
	
	public List<Event> findEventsByUserId(Long userId){
		
		return eventRepository.findEventsByUserId(userId);
		
	}
	
	public Page<Event> findEventsByUserId(Long userId, Pageable pageable) {
	    return eventRepository.findEventsByUserId(userId, pageable);
	}
	
	public Long deleteEventsByUserId(Long userId) {
		
		Long rtr = eventRepository.deleteEventsByEventId(userId);
		
		return rtr;
		
	}


}
