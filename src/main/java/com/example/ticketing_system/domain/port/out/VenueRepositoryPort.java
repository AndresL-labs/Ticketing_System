package com.example.ticketing_system.domain.port.out;

import com.example.ticketing_system.domain.model.Venue;

import java.util.List;

public interface VenueRepositoryPort {
    Venue findById(Long id);
    List<Venue> findAll(int page, int size);
    Venue save(Venue venue);
    Venue update(Long id, Venue venue);
    void delete(Long id);
}
