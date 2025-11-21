package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.mapper.EventMapper;
import com.example.ticketing_system.model.Event;
import com.example.ticketing_system.repository.IEventRepository;
import com.example.ticketing_system.service.IEventService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")
public class EventServiceMock implements IEventService {

    private final IEventRepository eventRepository;

    public EventServiceMock(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<ResponseEventDTO> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public ResponseEventDTO save(RequestEventDTO dto) {
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
