package com.example.ticketing_system.infrastructure.adapter.in.mapper;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseVenueDTO;
import org.springframework.stereotype.Component;

@Component
public class VenueWebMapper {
    public Venue toDomain(RequestVenueDTO dto) {
        return new Venue(
                dto.getVenueName(),
                dto.getLocation(),
                dto.getCapacity()
        );
    }

    public ResponseVenueDTO toResponse(Venue domain) {
        return new ResponseVenueDTO(
                domain.getId(),
                domain.getVenueName(),
                domain.getLocation(),
                domain.getCapacity()
        );
    }
}
