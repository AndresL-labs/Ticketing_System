package com.example.ticketing_system.service;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;

import java.util.List;

public interface IEventService {
    List<ResponseEventDTO> findAll();
    ResponseEventDTO save(RequestEventDTO eventDTO);
    ResponseEventDTO update(Long id, RequestEventDTO event);
    ResponseEventDTO delete(Long id);
}