package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface ManageEventUseCase {
    Event getEventById(Long id);
    Page<Event> getAllEvents(String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size);
    Event createEvent(Event newEvent);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}