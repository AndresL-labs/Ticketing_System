package com.example.ticketing_system.repository;

import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.model.Venue;

import java.util.List;

public interface IVenueRepository {
    List<ResponseVenueDTO> findAll();
    ResponseVenueDTO createVenue(Venue newVenue);
    ResponseVenueDTO updateVenue(Long id,Venue venue);
    ResponseVenueDTO deleteVenue(Long id);
}
