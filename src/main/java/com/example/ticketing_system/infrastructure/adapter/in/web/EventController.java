package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.*;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.mapper.EventWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventWebMapper eventWebMapper;

    private final IEventCreateUseCase create;
    private final IEventDeleteUseCase delete;
    private final IEventGetAllUseCase getAll;
    private final IEventGetByIdUseCase getById;
    private final IEventUpdateUseCase update;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> getEventById(@PathVariable Long id) {
        Event event = getById.getEventById(id);
        return ResponseEntity.ok(eventWebMapper.toDTO(event));
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
        Page<Event> events = getAll.getAllEvents(eventName, location, startDate, endDate, page, size);
        Page<ResponseEventDTO> response = events.map(eventWebMapper::toDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseEventDTO> createEvent(
            @RequestBody RequestEventDTO event
    ) {
        Event newEvent = create.createEvent(eventWebMapper.toDomain(event));
        ResponseEventDTO responseDTO = eventWebMapper.toDTO(newEvent);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> updateEvent(@PathVariable Long id, @RequestBody RequestEventDTO eventDTO) {
        Event updatedEvent = update.updateEvent(id, eventWebMapper.toDomain(eventDTO));
        ResponseEventDTO responseDTO = eventWebMapper.toDTO(updatedEvent);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        delete.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
