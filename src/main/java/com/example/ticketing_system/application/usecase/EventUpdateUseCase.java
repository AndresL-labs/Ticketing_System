package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.IEventUpdateUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;

public class EventUpdateUseCase implements IEventUpdateUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventUpdateUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        return eventRepositoryPort.findById(id);
    }
}
