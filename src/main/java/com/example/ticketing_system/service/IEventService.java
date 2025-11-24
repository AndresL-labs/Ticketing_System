package com.example.ticketing_system.service;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IEventService {
    Page<ResponseEventDTO> findAll(
            String name, String location, LocalDateTime start, LocalDateTime end, int page, int size);
    ResponseEventDTO save(RequestEventDTO eventDTO);
    ResponseEventDTO update(Long id, RequestEventDTO event);
    ResponseEventDTO delete(Long id);
}