package com.example.ticketing_system.repository;

import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.model.Event;

import java.util.List;

public interface IEventRepository {
    List<ResponseEventDTO> findAll();
    ResponseEventDTO save(Event newEvent);
    ResponseEventDTO update(Long id, Event event);
    ResponseEventDTO delete(Long id);
}
