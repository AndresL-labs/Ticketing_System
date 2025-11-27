package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Venue;

public interface IVenueCreateUseCase {
    Venue createVenue(Venue venue);
}
