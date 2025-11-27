package com.example.ticketing_system.infrastructure.adapter.in.mapper;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseVenueDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface VenueWebMapper {

    // VenueWebMapper INSTANCE = Mappers.getMapper(VenueWebMapper.class);
    // En este caso no es necesario el INSTANCE porque Spring se encarga de la inyección de dependencias usando el componentModel = "spring"

    Venue toDomain(RequestVenueDTO dto);
    ResponseVenueDTO toResponse(Venue domain);
}