package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.IVenueGetAllUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class VenueGetAllUseCase implements IVenueGetAllUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public VenueGetAllUseCase(VenueRepositoryPort venueRepositoryPort){
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Page<Venue> getAllVenues(Integer minCapacity , int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return venueRepositoryPort.findAll(minCapacity, pageable);
    }
}
