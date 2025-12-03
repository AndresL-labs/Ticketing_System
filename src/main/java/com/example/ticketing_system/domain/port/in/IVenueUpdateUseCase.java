package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Venue;

public interface IVenueUpdateUseCase {
    Venue updateVenue(Long id, Venue venue);
}
