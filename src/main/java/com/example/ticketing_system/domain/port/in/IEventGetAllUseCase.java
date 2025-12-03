package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface IEventGetAllUseCase {
    Page<Event> getAllEvents(String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size);
}
