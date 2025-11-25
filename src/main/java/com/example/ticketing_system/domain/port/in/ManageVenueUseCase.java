package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Venue;

import java.util.List;

public interface ManageVenueUseCase {
    Venue getVenueById(Long id);
    List<Venue> getAllVenues(int page, int size);
    Venue createVenue(Venue newVenue);
    Venue updateVenue(Long id, Venue venue);
    void deleteVenue(Long id);
}
