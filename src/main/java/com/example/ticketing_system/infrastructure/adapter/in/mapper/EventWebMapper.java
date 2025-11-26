package com.example.ticketing_system.infrastructure.adapter.in.mapper;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseEventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventWebMapper {
    EventWebMapper INSTANCE = Mappers.getMapper(EventWebMapper.class);
    @Mapping(source = "venueId", target = "venue")
    Event toDomain(RequestEventDTO dto);
    @Mapping(source = "venue", target = "venueId")
    ResponseEventDTO toDTO(Event event);

    // Long → Venue
    default Venue map(Long venueId) {
        if (venueId == null) return null;
        Venue venue = new Venue();
        venue.setId(venueId);
        return venue;
    }

    // Venue → Long
    default Long map(Venue venue) {
        return venue == null ? null : venue.getId();
    }
}
