package com.example.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class DeleteApi {
	
	private final EventService eventService;

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Long eventId) {
    	
    	System.out.println("try to delete " + eventId);
    	
        eventService.deleteEventsByUserId(eventId);
        return ResponseEntity.noContent().build();  // 삭제 후 응답 코드 204 (No Content) 반환
    }

}
