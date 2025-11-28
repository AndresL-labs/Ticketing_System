package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;

public interface IEventUpdateUseCase {
    Event updateEvent(Long id, Event event);
}
