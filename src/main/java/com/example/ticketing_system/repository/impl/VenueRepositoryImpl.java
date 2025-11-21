package com.example.ticketing_system.repository.impl;

import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.mapper.VenueMapper;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.IVenueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VenueRepositoryImpl implements IVenueRepository {

    private final List<Venue> venueList = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public List<ResponseVenueDTO> findAll() {
        return VenueMapper.INSTANCE.toDTOList(venueList);
    }

    @Override
    public ResponseVenueDTO createVenue(Venue venue) {
        venue.setId(idGen.getAndIncrement());
        venueList.add(venue);
        return VenueMapper.INSTANCE.toDTO(venue);
    }

    @Override
    public ResponseVenueDTO updateVenue(Long id, Venue venue) {
        Venue venueToUpdate = venueList.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado"));
        venueToUpdate.setVenueName(venue.getVenueName());
        venueToUpdate.setCapacity(venue.getCapacity());
        venueToUpdate.setLocation(venue.getLocation());
        return VenueMapper.INSTANCE.toDTO(venueToUpdate);
    }

    @Override
    public ResponseVenueDTO deleteVenue(Long id) {
        Venue venueToDelete = venueList.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar no encontrado"));
        venueList.remove(venueToDelete);
        return VenueMapper.INSTANCE.toDTO(venueToDelete);
    }
}