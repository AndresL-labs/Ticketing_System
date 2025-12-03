package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.port.in.IVenueDeleteUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class VenueDeleteUseCase implements IVenueDeleteUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public VenueDeleteUseCase (VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public void deleteVenue(Long id) {
        venueRepositoryPort.delete(id);
    }
}
