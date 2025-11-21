package com.example.ticketing_system.repository.impl;

import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.mapper.EventMapper;
import com.example.ticketing_system.model.Event;
import com.example.ticketing_system.repository.IEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EventRepositoryImpl implements IEventRepository {

    private final List<Event> events = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<ResponseEventDTO> findAll() {
        return EventMapper.INSTANCE.toDTOList(events);
    }

    @Override
    public ResponseEventDTO save(Event newEvent) {
        newEvent.setId(idGen.getAndIncrement());
        events.add(newEvent);
        return EventMapper.INSTANCE.toDTO(newEvent);
    }

    @Override
    public ResponseEventDTO update(Long id, Event event) {
        Event eventToUpdate = events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));
        eventToUpdate.setEventName(event.getEventName());
        eventToUpdate.setEventDate(event.getEventDate());
        return EventMapper.INSTANCE.toDTO(eventToUpdate);
    }

    @Override
    public ResponseEventDTO delete(Long id) {
        Event eventToDelete = events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado"));
        events.remove(eventToDelete);
        return EventMapper.INSTANCE.toDTO(eventToDelete);
    }
}
