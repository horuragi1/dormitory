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
	
	public Event findByEventId(Long eventId) {
		
		return eventRepository.findByEventId(eventId);
		
	}
	
	@Transactional
    public Event updateEvent(Long eventId, Event updatedEvent) {
        Event existingEvent = eventRepository.findByEventId(eventId);

        if (existingEvent != null) {
            existingEvent.setTitle(updatedEvent.getTitle());
            existingEvent.setStart_date(updatedEvent.getStart_date());
            existingEvent.setEnd_date(updatedEvent.getEnd_date());
            existingEvent.setAllDay(true);
            existingEvent.setColor(updatedEvent.getColor());
            existingEvent.setTextColor(updatedEvent.getTextColor());
            // 다른 필드도 필요에 따라 업데이트합니다.
            
            eventRepository.save(existingEvent);
        }

        return existingEvent;
    }


}
