package com.example.ticketing_system.infrastructure.adapter.out.persistence.adapter;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper.EventMapper;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.EventJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventJpaRepository eventJpaRepository;

    public EventRepositoryAdapter(EventJpaRepository eventJpaRepository) {
        this.eventJpaRepository = eventJpaRepository;
    }

    @Override
    public Event findById(Long id) {
        return eventJpaRepository.findById(id).map(EventMapper.INSTANCE::toDomain)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public List<Event> findAll(int page, int size) {
        return eventJpaRepository.findAll()
                .stream()
                .map(EventMapper.INSTANCE::toDomain)
                .toList();
    }

    @Override
    public Event save(Event event) {
        return EventMapper.INSTANCE.toDomain(
                eventJpaRepository.save(
                        EventMapper.INSTANCE.toEntity(event)
                )
        );
    }

    @Override
    public Event update(Long id, Event event) {
        EventEntity eventToUpdate = eventJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        if (event.getEventName() != null) {
            eventToUpdate.setEventName(event.getEventName());
        }
        if (event.getEventDate() != null) {
            eventToUpdate.setEventDate(event.getEventDate());
        }

        return EventMapper.INSTANCE.toDomain(
                eventJpaRepository.save(eventToUpdate)
        );
    }

    @Override
    public void delete(Long id) {
    EventEntity eventToDelete = eventJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
    eventJpaRepository.delete(eventToDelete);
    }
}