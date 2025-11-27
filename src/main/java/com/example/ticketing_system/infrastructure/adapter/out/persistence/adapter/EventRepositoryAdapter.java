package com.example.ticketing_system.infrastructure.adapter.out.persistence.adapter;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper.EventMapper;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.EventJpaRepository;
import com.example.ticketing_system.infrastructure.spec.EventSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    //Event
    @Override
    public Page<Event> findAll(String eventName, String location, LocalDateTime start, LocalDateTime end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<EventEntity> spec = (root, query, cb) -> null;

        if (eventName != null && !eventName.isBlank()) {
            spec = spec.and(EventSpecification.nameLike(eventName));
        }
        if (location != null && !location.isBlank()) {
            spec = spec.and(EventSpecification.locationEquals(location));
        }
        if (start != null) {
            spec = spec.and(EventSpecification.dateFrom(start));
        }
        if (end != null) {
            spec = spec.and(EventSpecification.dateTo(end));
        }

        return eventJpaRepository.findAll(spec, pageable)
                .map(EventMapper.INSTANCE::toDomain);

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
        com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity eventToUpdate = eventJpaRepository.findById(id)
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
    com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity eventToDelete = eventJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));
    eventJpaRepository.delete(eventToDelete);
    }
}