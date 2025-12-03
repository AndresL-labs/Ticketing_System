package com.example.ticketing_system.domain.port.out;

import com.example.ticketing_system.domain.model.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VenueRepositoryPort {
    Venue findById(Long id);
    Page<Venue> findAll(Integer minCapacity, Pageable pageable);
    Venue save(Venue venue);
    Venue update(Long id, Venue venue);
    void delete(Long id);
}
