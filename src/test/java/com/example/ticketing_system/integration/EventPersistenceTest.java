package com.example.ticketing_system.integration;

import com.example.ticketing_system.config.TestContainerConfig;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.EventJpaRepository;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestContainerConfig.class)
class EventPersistenceTest {

    @Autowired
    private EventJpaRepository eventJpaRepository;

    @Autowired
    private VenueJpaRepository venueJpaRepository;

    @BeforeEach
    void setup() {
        eventJpaRepository.deleteAll();
        venueJpaRepository.deleteAll();
    }

    @Test
    void shouldPersistEventInRealDatabase() {
        // Arrange: Create and persist a Venue first
        VenueEntity venue = new VenueEntity();
        venue.setVenueName("Test Venue");
        venue.setLocation("Test Location");
        venue.setCapacity(500);
        VenueEntity savedVenue = venueJpaRepository.save(venue);

        // Arrange: Create the Event and associate the Venue
        EventEntity entity = new EventEntity();
        entity.setEventName("Real DB Event");
        entity.setEventDate(LocalDateTime.now());
        entity.setVenue(savedVenue);

        // Act
        EventEntity saved = eventJpaRepository.save(entity);

        // Assert
        assertNotNull(saved.getId());
        EventEntity found = eventJpaRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Real DB Event", found.getEventName());
        assertNotNull(found.getVenue());
        assertEquals(savedVenue.getId(), found.getVenue().getId());
    }
}
