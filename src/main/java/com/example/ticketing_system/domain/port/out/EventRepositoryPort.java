package com.example.ticketing_system.domain.port.out;

import com.example.ticketing_system.domain.model.Event;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface EventRepositoryPort {
    Event findById(Long id);
    Page<Event> findAll(String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size);
    Event save(Event event);
    Event update(Long id, Event event);
    void delete(Long id);
}
