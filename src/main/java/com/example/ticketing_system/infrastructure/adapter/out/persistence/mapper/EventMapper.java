package com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseEventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toEntity(RequestEventDTO eventDTO);
    ResponseEventDTO toDTO(Event event);
    List<ResponseEventDTO> toDTOList(List<Event> events);
}
