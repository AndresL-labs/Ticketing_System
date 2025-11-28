package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.in.IEventGetAllUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public class EventGetAllUseCase implements IEventGetAllUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventGetAllUseCase(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Page<Event> getAllEvents(
            String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size
    ) {
        return eventRepositoryPort.findAll(eventName, location, start, end, page, size);
    }
}
