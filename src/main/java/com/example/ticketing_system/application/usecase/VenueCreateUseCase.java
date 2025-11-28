package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.IVenueCreateUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class VenueCreateUseCase implements IVenueCreateUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public VenueCreateUseCase(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue createVenue(Venue venue) {
        return venueRepositoryPort.save(venue);
    }
}
