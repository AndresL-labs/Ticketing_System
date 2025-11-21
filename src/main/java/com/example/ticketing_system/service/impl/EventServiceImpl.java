package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.mapper.EventMapper;
import com.example.ticketing_system.model.Event;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.EventRepository;
import com.example.ticketing_system.repository.IEventRepository;
import com.example.ticketing_system.repository.VenueRepository;
import com.example.ticketing_system.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("dev")
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Override
    public List<ResponseEventDTO> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public ResponseEventDTO save(RequestEventDTO dto) {
        Venue venue = venueRepository.findById(dto.getVenueId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found with id: " + dto.getVenueId()));
        Event event = EventMapper.INSTANCE.toEntity(dto);
        return eventRepository.save(event);
    }

    @Override
    public ResponseEventDTO update(Long id, RequestEventDTO event) {
        Event eventToUpdate = EventMapper.INSTANCE.toEntity(event);
        return eventRepository.update(id, eventToUpdate);
    }

    @Override
    public ResponseEventDTO delete(Long id) {
        return eventRepository.delete(id);
    }
}
