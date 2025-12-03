package com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toDomain(EventEntity eventEntity);
    EventEntity toEntity(Event event);

}
