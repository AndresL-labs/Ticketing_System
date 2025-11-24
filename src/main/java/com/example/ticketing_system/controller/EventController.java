package com.example.ticketing_system.controller;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.service.IEventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
public class EventController {

    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseEventDTO>> getAllEvents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(eventService.findAll(name, location, startDate, endDate, page, size));
    }

    @PostMapping
    public ResponseEntity<ResponseEventDTO> createEvent(@Valid @RequestBody RequestEventDTO eventDTO) {
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
