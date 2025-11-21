package com.example.ticketing_system.repository;

import com.example.ticketing_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
