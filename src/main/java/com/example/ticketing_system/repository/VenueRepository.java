package com.example.ticketing_system.repository;

import com.example.ticketing_system.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue,Long> {
}
