package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.mapper.EventMapper;
import com.example.ticketing_system.model.Event;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.EventRepository;
import com.example.ticketing_system.repository.VenueRepository;
import com.example.ticketing_system.service.IEventService;
import com.example.ticketing_system.service.spec.EventSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("dev")
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Override
    public Page<ResponseEventDTO> findAll(
            String name, String location, LocalDateTime start, LocalDateTime end, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        // 1. Creamos una lista mutable para guardar las condiciones
        List<Specification<Event>> specs = new ArrayList<>();
        // 2. Agregamos condiciones a la lista solo si existen
        if (name != null && !name.isBlank()) {
            specs.add(EventSpecification.nameLike(name));
        }
        if (location != null && !location.isBlank()) {
            specs.add(EventSpecification.locationEquals(location));
        }
        if (start != null) {
            specs.add(EventSpecification.dateFrom(start));
        }
        if (end != null) {
            specs.add(EventSpecification.dateTo(end));
        }
        // 3. Usamos Specification.allOf() que une todo con AND
        // Si la lista está vacía, allOf devuelve una especificación "vacia" válida automáticamente
        Specification<Event> finalSpec = Specification.allOf(specs);
        return eventRepository.findAll(finalSpec, pageable)
                .map(EventMapper.INSTANCE::toDTO);
    }

    @Override
    public ResponseEventDTO save(RequestEventDTO dto) {
        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Lugar de evento con id: " + dto.getVenueId() + " no encontrado"));
        if (eventRepository.existsEventByEventName(dto.getEventName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Evento con este nombre ya existe.");
        }
        Event event = EventMapper.INSTANCE.toEntity(dto);
        event.setVenue(venue);
        Event eventSaved = eventRepository.save(event);
        return EventMapper.INSTANCE.toDTO(eventSaved);
    }

    @Override
    public ResponseEventDTO update(Long id, RequestEventDTO event){
        Event eventToUpdate = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento con id: " + id +
                        " no encontrado."));

        eventToUpdate.setEventName(event.getEventName());
        eventToUpdate.setEventDate(event.getEventDate());

        Venue venue = venueRepository.findById(event.getVenueId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar de evento con id: " +
                        event.getVenueId() + " no encontrado."));
        eventToUpdate.setVenue(venue);
        Event updatedEvent = eventRepository.save(eventToUpdate);
        return EventMapper.INSTANCE.toDTO(updatedEvent);
    }

    @Override
    public ResponseEventDTO delete(Long id) {
        Event eventDeleted = eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento con id: " + id +
                        " no encontrado."));
        eventRepository.delete(eventDeleted);
        return EventMapper.INSTANCE.toDTO(eventDeleted);
    }
}
