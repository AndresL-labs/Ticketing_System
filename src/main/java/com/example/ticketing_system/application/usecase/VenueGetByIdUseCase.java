package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.IVenueGetByIdUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;

public class VenueGetByIdUseCase implements IVenueGetByIdUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public VenueGetByIdUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue getVenueById(Long id) {
        return venueRepositoryPort.findById(id);
    }
}