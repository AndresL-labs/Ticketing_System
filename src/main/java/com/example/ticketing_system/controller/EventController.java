package com.example.ticketing_system.controller;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.service.IEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseEventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PostMapping
    public ResponseEntity<ResponseEventDTO> createEvent(@RequestBody RequestEventDTO eventDTO) {
        ResponseEventDTO createdEvent = eventService.save(eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> updateEvent(@RequestBody RequestEventDTO eventDTO, @PathVariable Long id) {
        ResponseEventDTO updatedEvent = eventService.update(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> deleteEvent(@PathVariable Long id) {
        ResponseEventDTO deletedEvent = eventService.delete(id);
        return ResponseEntity.ok(deletedEvent);
    }
}
