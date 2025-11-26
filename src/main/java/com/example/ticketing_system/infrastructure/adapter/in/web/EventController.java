package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.ManageEventUseCase;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.mapper.EventWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ResponseEventDTO>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<Event> events = manageEventUseCase.getAllEvents(page, size);
        List<ResponseEventDTO> responseDTOs = events.stream()
                .map(EventWebMapper.INSTANCE::toDTO)
                .toList();
        return ResponseEntity.ok(responseDTOs);
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
