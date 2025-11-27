package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.IEventGetByIdUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;

public class EventGetByIdUseCase implements IEventGetByIdUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventGetByIdUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepositoryPort.findById(id);
    }
}
