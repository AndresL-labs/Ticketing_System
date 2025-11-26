package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.ManageEventUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.domain.service.EventDateValidation;

import java.util.List;

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
    public List<Event> getAllEvents(int page, int size) {
        return eventRepositoryPort.findAll(page, size);
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
