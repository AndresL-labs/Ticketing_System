package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.mapper.VenueMapper;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.IVenueRepository;
import com.example.ticketing_system.service.IVenueService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("dev")
public class VenueServiceImpl implements IVenueService {

    private final IVenueRepository venueRepository;

    public VenueServiceImpl(IVenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<ResponseVenueDTO> findAll() {
        return venueRepository.findAll();
    }

    @Override
    public ResponseVenueDTO createVenue(RequestVenueDTO venueDTO) {
        Venue venue = VenueMapper.INSTANCE.toEntity(venueDTO);
        return venueRepository.createVenue(venue);
    }

    @Override
    public ResponseVenueDTO updateVenue(Long id, RequestVenueDTO venueDTO) {
        Venue venue = VenueMapper.INSTANCE.toEntity(venueDTO);
        return venueRepository.updateVenue(id, venue);
    }

    @Override
    public ResponseVenueDTO deleteVenue(Long id) {
        return venueRepository.deleteVenue(id);
    }
}
