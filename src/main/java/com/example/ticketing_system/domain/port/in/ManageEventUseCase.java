package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Event;

import java.util.List;

public interface ManageEventUseCase {
    Event getEventById(Long id);
    List<Event> getAllEvents(int page, int size);
    Event createEvent(Event newEvent);
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
}