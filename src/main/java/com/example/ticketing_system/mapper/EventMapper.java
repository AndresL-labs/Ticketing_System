package com.example.ticketing_system.mapper;

import com.example.ticketing_system.dto.RequestEventDTO;
import com.example.ticketing_system.dto.ResponseEventDTO;
import com.example.ticketing_system.model.Event;
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
