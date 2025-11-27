package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.ManageEventUseCase;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.mapper.EventWebMapper;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
public class EventController {

    private final ManageEventUseCase manageEventUseCase;

    public EventController(ManageEventUseCase manageEventUseCase) {
        this.manageEventUseCase = manageEventUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> getEventById(@PathVariable Long id) {
        Event event = manageEventUseCase.getEventById(id);
        return ResponseEntity.ok(EventWebMapper.INSTANCE.toDTO(event));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseEventDTO>> getAllEvents(
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String location,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Event> events = manageEventUseCase.getAllEvents(eventName, location, startDate, endDate, page, size);
        Page<ResponseEventDTO> response = events.map(EventWebMapper.INSTANCE::toDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseEventDTO> createEvent(
            @RequestBody RequestEventDTO event
    ) {
        Event newEvent = manageEventUseCase.createEvent(EventWebMapper.INSTANCE.toDomain(event));
        ResponseEventDTO responseDTO = EventWebMapper.INSTANCE.toDTO(newEvent);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> updateEvent(@PathVariable Long id, @RequestBody RequestEventDTO eventDTO) {
        Event updatedEvent = manageEventUseCase.updateEvent(id, EventWebMapper.INSTANCE.toDomain(eventDTO));
        ResponseEventDTO responseDTO = EventWebMapper.INSTANCE.toDTO(updatedEvent);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        manageEventUseCase.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
