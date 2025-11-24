package com.example.ticketing_system.service.impl;

import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.mapper.VenueMapper;
import com.example.ticketing_system.model.Venue;
import com.example.ticketing_system.repository.VenueRepository;
import com.example.ticketing_system.service.IVenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class VenueServiceImpl implements IVenueService {

    private final VenueRepository venueRepository;

    @Override
    public Page<ResponseVenueDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Venue> venues = venueRepository.findAll(pageable);
        return venues.map(VenueMapper.INSTANCE::toDTO);
    }

    @Override
    public ResponseVenueDTO createVenue(RequestVenueDTO venueDTO) {
        Venue venue = VenueMapper.INSTANCE.toEntity(venueDTO);
        return VenueMapper.INSTANCE.toDTO(venueRepository.save(venue));
    }

    @Override
    public ResponseVenueDTO updateVenue(Long id, RequestVenueDTO venueDTO){
        Venue venueToUpdate = venueRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lugar de evento con id: " + id +
                        " no encontrado."));
        venueToUpdate.setVenueName(venueDTO.getVenueName());
        venueToUpdate.setCapacity(venueDTO.getCapacity());
        venueToUpdate.setLocation(venueDTO.getLocation());
        Venue venueUpdated = venueRepository.save(venueToUpdate);
        return VenueMapper.INSTANCE.toDTO(venueUpdated);
    }

    @Override
    public ResponseVenueDTO deleteVenue(Long id) {
        Venue venueToDelete = venueRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró lugar de evento" +
                        "con id: " + id));
        try {
            venueRepository.delete(venueToDelete);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No puede eliminar un lugar de evento si tiene un evento asociado.");
        }
        return VenueMapper.INSTANCE.toDTO(venueToDelete);
    }
}
