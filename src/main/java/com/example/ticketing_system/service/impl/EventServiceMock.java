package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.mapper.EventMapper;
import com.example.ticketing_system.model.Event;
import com.example.ticketing_system.repository.IEventRepository;
import com.example.ticketing_system.service.IEventService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@Profile("test")
public class EventServiceMock implements IEventService {

    private final IEventRepository eventRepository;

    public EventServiceMock(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Page<ResponseEventDTO> findAll(
            String name,
            String location,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int page,
            int size
    ) {
        List<ResponseEventDTO> events = eventRepository.findAll();

        List<ResponseEventDTO> filtered = events.stream()
                .filter(e -> name == null ||
                        e.getEventName().toLowerCase().contains(name.toLowerCase()))
                .filter(e -> startDate == null ||
                        e.getEventDate().isAfter(startDate))
                .filter(e -> endDate == null ||
                        e.getEventDate().isBefore(endDate))
                .toList();

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<ResponseEventDTO> paginated =
                start <= filtered.size() ? filtered.subList(start, end) : List.of();

        return new PageImpl<>(paginated, pageable, filtered.size());
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