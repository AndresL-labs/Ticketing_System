package com.example.ticketing_system.domain.port.out;

import com.example.ticketing_system.domain.model.Event;

import java.util.List;

public interface EventRepositoryPort {
    Event findById(Long id);
    List<Event> findAll(int page, int size);
    Event save(Event event);
    Event update(Long id, Event event);
    void delete(Long id);
}
