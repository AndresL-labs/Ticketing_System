package com.example.ticketing_system.service;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVenueService {
    Page<ResponseVenueDTO> findAll(int page, int size);
    ResponseVenueDTO createVenue(RequestVenueDTO venueDTO);
    ResponseVenueDTO updateVenue(Long id, RequestVenueDTO venueDTO);
    ResponseVenueDTO deleteVenue(Long id);
}