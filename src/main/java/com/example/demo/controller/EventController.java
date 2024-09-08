package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.EventWithPeople;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable("eventId") Long eventId) {
        Event event = eventService.findByEventId(eventId);

        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable("eventId") Long eventId,
            @RequestBody Event updatedEvent) {
        
        Event event = eventService.updateEvent(eventId, updatedEvent);

        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/date/{date}/people")
    public ResponseEntity<Page<String>> getPeopleByDate(
            @PathVariable("date") String date,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
    	
    	System.out.println("find people names!!!\n");
        
        // 날짜를 LocalDate로 변환
        LocalDate localDate = LocalDate.parse(date);
        
        // 날짜에 해당하는 이벤트를 가진 사람들의 이름을 페이지 단위로 조회
        Page<String> people = eventService.findPeopleByDate(localDate, PageRequest.of(page, size));
        
        return ResponseEntity.ok(people);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Page<EventWithPeople>> getAllEventsWithPeople(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
    	
    	System.out.println("events finding!!!");

        Page<EventWithPeople> events = eventService.findAllEventsWithPeople(PageRequest.of(page, size));

        return ResponseEntity.ok(events);
    }

    // Other methods (e.g., for saving, updating, and deleting events)
}
