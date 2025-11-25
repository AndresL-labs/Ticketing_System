package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.ManageVenueUseCase;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.mapper.VenueWebMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final ManageVenueUseCase manageVenueUseCase;
    private final VenueWebMapper venueWebMapper;

    public VenueController(ManageVenueUseCase manageVenueUseCase, VenueWebMapper venueWebMapper) {
        this.manageVenueUseCase = manageVenueUseCase;
        this.venueWebMapper = venueWebMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> getVenueById(@PathVariable Long id) {
        var venue = manageVenueUseCase.getVenueById(id);
        var responseDTO = venueWebMapper.toResponse(venue);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseVenueDTO>> getAllVenues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Venue> venues = manageVenueUseCase.getAllVenues(page, size);
        return ResponseEntity.ok(
                venues.stream().map(venueWebMapper::toResponse).toList()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseVenueDTO> createVenue(
            @RequestBody RequestVenueDTO requestVenueDTO
    ){
        Venue venue = venueWebMapper.toDomain(requestVenueDTO);
        Venue createdVenue = manageVenueUseCase.createVenue(venue);
        ResponseVenueDTO responseDTO = venueWebMapper.toResponse(createdVenue);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> updateVenue(
            @PathVariable Long id,
            @RequestBody RequestVenueDTO requestVenueDTO
    ){
        Venue venue = venueWebMapper.toDomain(requestVenueDTO);
        Venue updatedVenue = manageVenueUseCase.updateVenue(id, venue);
        ResponseVenueDTO responseDTO = venueWebMapper.toResponse(updatedVenue);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        manageVenueUseCase.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }

}
