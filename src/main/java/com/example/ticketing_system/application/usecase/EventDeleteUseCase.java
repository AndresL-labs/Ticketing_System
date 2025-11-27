package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.port.in.IEventDeleteUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;

public class EventDeleteUseCase implements IEventDeleteUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventDeleteUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    public void deleteEvent(Long id) {
        eventRepositoryPort.delete(id);
    }
}
