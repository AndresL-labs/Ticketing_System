package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.ManageEventUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.domain.service.EventDateValidation;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class EventUseCase implements ManageEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepositoryPort.findById(id);
    }

    @Override
    public Page<Event> getAllEvents(String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size) {
        return eventRepositoryPort.findAll(eventName, location, start, end, page, size);
    }

    @Override
    public Event createEvent(Event newEvent) {
        if (!EventDateValidation.validate(newEvent)){
            throw new IllegalArgumentException("Invalid event date");
        }
        return eventRepositoryPort.save(newEvent);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return eventRepositoryPort.update(id, event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepositoryPort.delete(id);
    }
}
