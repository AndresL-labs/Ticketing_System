package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.IVenueUpdateUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class VenueUpdateUseCase implements IVenueUpdateUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public VenueUpdateUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue updateVenue(Long id, Venue venue) {
        return venueRepositoryPort.update(id, venue);
    }
}
