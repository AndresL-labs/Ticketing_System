package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.IEventCreateUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EventCreateUseCase implements IEventCreateUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventCreateUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event createEvent(Event newEvent) {
        return eventRepositoryPort.save(newEvent);
    }
}
