package com.example.ticketing_system.controller;
import com.example.ticketing_system.dto.RequestVenueDTO;
import com.example.ticketing_system.dto.ResponseVenueDTO;
import com.example.ticketing_system.service.IVenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private final IVenueService venueService;

    public VenueController(IVenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseVenueDTO>> getAllVenue() {
        return ResponseEntity.ok(venueService.findAll());
    }

    @PostMapping
    public ResponseEntity<ResponseVenueDTO> createVenue(@RequestBody RequestVenueDTO venueDTO) {
        ResponseVenueDTO venueCreated = venueService.createVenue(venueDTO);
        return ResponseEntity.ok(venueCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> updateVenue(@PathVariable Long id, @RequestBody RequestVenueDTO venueDTO) {
        ResponseVenueDTO venueUpdated = venueService.updateVenue(id, venueDTO);
        return ResponseEntity.ok(venueUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseVenueDTO> deleteVenue(@PathVariable Long id) {
        ResponseVenueDTO venueDeleted = venueService.deleteVenue(id);
        return ResponseEntity.ok(venueDeleted);
    }
}
