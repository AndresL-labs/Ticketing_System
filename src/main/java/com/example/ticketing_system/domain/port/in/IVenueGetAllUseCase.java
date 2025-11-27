package com.example.ticketing_system.domain.port.in;

import com.example.ticketing_system.domain.model.Venue;
import org.springframework.data.domain.Page;

public interface IVenueGetAllUseCase {
    Page<Venue> getAllVenues(Integer minCapacity, int page, int size);
}
