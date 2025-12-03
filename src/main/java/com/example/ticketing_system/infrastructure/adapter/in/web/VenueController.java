package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.in.*;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.dto.ResponseVenueDTO;
import com.example.ticketing_system.infrastructure.adapter.in.mapper.VenueWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueWebMapper venueWebMapper;

    private final IVenueCreateUseCase create;
    private final IVenueDeleteUseCase delete;
    private final IVenueGetAllUseCase getAll;
    private final IVenueGetByIdUseCase getById;
    private final IVenueUpdateUseCase update;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> getVenueById(@PathVariable Long id) {
        var venue = getById.getVenueById(id);
        var responseDTO = venueWebMapper.toResponse(venue);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseVenueDTO>> getAllVenues(
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Venue> venues = getAll.getAllVenues(minCapacity, page, size);
        return ResponseEntity.ok(
                venues.map(venueWebMapper::toResponse)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseVenueDTO> createVenue(
            @RequestBody RequestVenueDTO requestVenueDTO
    ){
        Venue venue = venueWebMapper.toDomain(requestVenueDTO);
        Venue createdVenue = create.createVenue(venue);
        ResponseVenueDTO responseDTO = venueWebMapper.toResponse(createdVenue);

        // Construir la URI del nuevo recurso creado
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVenue.getId())
                .toUri();

        // Devolver 201 Created con la ubicación y el cuerpo del recurso
        return ResponseEntity.created(location).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> updateVenue(
            @PathVariable Long id,
            @RequestBody RequestVenueDTO requestVenueDTO
    ){
        Venue venue = venueWebMapper.toDomain(requestVenueDTO);
        Venue updatedVenue = update.updateVenue(id, venue);
        ResponseVenueDTO responseDTO = venueWebMapper.toResponse(updatedVenue);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        delete.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }

}
