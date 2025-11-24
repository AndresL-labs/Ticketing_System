package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.mapper.VenueMapper;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.IVenueRepository;
import com.example.ticketing_system.service.IVenueService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("test")
public class VenueServiceMock implements IVenueService {

    private final IVenueRepository venueRepository;

    public VenueServiceMock(IVenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Page<ResponseVenueDTO> findAll(int page, int size) {
        List<ResponseVenueDTO> venues = venueRepository.findAll();
        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<ResponseVenueDTO>(venues, pageable, venues.size());
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