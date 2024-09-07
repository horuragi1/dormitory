package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
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

    // Other methods (e.g., for saving, updating, and deleting events)
}
