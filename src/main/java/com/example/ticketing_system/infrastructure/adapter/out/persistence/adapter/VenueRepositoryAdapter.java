package com.example.ticketing_system.infrastructure.adapter.out.persistence.adapter;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.mapper.VenueMapper;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class VenueRepositoryAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository venueJpaRepository;
    private final VenueMapper mapper;

    public VenueRepositoryAdapter(VenueJpaRepository venueJpaRepository, VenueMapper venueMapper) {
        this.venueJpaRepository = venueJpaRepository;
        this.mapper = venueMapper;
    }

    @Override
    public Venue findById(Long id) {
        return venueJpaRepository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    @Override
    public Page<Venue> findAll(Integer minCapacity, Pageable pageable) {

        Page<VenueEntity> page;

        if (minCapacity != null){
            page = venueJpaRepository.findByCapacity(minCapacity, pageable);
        } else {
            page = venueJpaRepository.findAll(pageable);
        }

        return page.map(mapper::toDomain);
    }

    @Override
    public Venue save(Venue venue) {
        return mapper.toDomain(
                venueJpaRepository.save(
                        mapper.toEntity(venue)
                )
        );
    }

    @Override
    public Venue update(Long id, Venue venue) {
        VenueEntity existingVenue = venueJpaRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Venue not found"));

        if (venue.getVenueName() != null) {
            existingVenue.setVenueName(venue.getVenueName());
        }
        if (venue.getLocation() != null) {
            existingVenue.setLocation(venue.getLocation());
        }
        if (venue.getCapacity() != 0) {
            existingVenue.setCapacity(venue.getCapacity());
        }
        return mapper.toDomain(venueJpaRepository.save(existingVenue));
    }

    @Override
    public void delete(Long id) {
        VenueEntity venueToDelete = venueJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        venueJpaRepository.delete(venueToDelete);
    }
}
