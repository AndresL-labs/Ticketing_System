package com.example.ticketing_system.service;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;

import java.util.List;

public interface IVenueService {
    List<ResponseVenueDTO> findAll();
    ResponseVenueDTO createVenue(RequestVenueDTO venueDTO);
    ResponseVenueDTO updateVenue(Long id, RequestVenueDTO venueDTO);
    ResponseVenueDTO deleteVenue(Long id);
}
