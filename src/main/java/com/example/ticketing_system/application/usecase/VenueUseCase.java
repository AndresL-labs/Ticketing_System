package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.ManageVenueUseCase;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;

import java.util.List;

public class VenueUseCase implements ManageVenueUseCase {

    private final VenueRepositoryPort venueRepository;

    public VenueUseCase(VenueRepositoryPort venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id);
    }

    @Override
    public List<Venue> getAllVenues(int page, int size) {
        return venueRepository.findAll(page, size);
    }

    @Override
    public Venue createVenue(Venue newVenue) {
        // Reglas de negocio: validar nombre y capacidad.
        return venueRepository.save(newVenue);
    }

    @Override
    public Venue updateVenue(Long id, Venue venue) {
        return venueRepository.update(id, venue);
    }

    @Override
    public void deleteVenue(Long id) {
        venueRepository.delete(id);
    }
}
