package com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.domain.model.Venue;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    VenueEntity toEntity(Venue domain);
    Venue toDomain(VenueEntity entity);
}