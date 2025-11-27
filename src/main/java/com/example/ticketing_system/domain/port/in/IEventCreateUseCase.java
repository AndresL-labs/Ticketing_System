package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;

public interface IEventCreateUseCase {
    Event createEvent(Event newEvent);
}
