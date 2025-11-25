package com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.domain.model.Venue;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {
    public VenueEntity toEntity(Venue domain) {
        if (domain == null) return null;

        VenueEntity entity = new VenueEntity();
        entity.setId(domain.getId());
        entity.setVenueName(domain.getVenueName());
        entity.setLocation(domain.getLocation());
        entity.setCapacity(domain.getCapacity());
        return entity;
    }

    public Venue toDomain(VenueEntity entity) {
        if (entity == null) return null;

        return new Venue(
                entity.getId(),
                entity.getVenueName(),
                entity.getLocation(),
                entity.getCapacity()
        );
    }
}
