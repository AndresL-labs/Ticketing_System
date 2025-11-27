package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;

public interface IEventGetByIdUseCase {
    Event getEventById(Long id);
}
