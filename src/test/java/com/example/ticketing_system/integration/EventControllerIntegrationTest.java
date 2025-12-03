package com.example.ticketing_system.integration;

import com.example.ticketing_system.infrastructure.adapter.in.dto.RequestEventDTO;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.EventJpaRepository;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class EventControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EventJpaRepository eventJpaRepository;

    @Autowired
    private VenueJpaRepository venueJpaRepository;

    private VenueEntity venue;

    @BeforeEach
    void setup() {
        eventJpaRepository.deleteAll();
        venueJpaRepository.deleteAll();
        venue = new VenueEntity();
        venue.setVenueName("Test Venue");
        venue.setLocation("Test Location");
        venue.setCapacity(100);
        venue = venueJpaRepository.save(venue);
    }

    @Test
    void shouldCreateEventSuccessfully() throws Exception {
        RequestEventDTO dto = new RequestEventDTO();
        dto.setEventName("Test Event");
        // Use future dates to pass validation
        dto.setEventDate(LocalDateTime.now().plusDays(1));
        dto.setEventEndDate(LocalDateTime.now().plusDays(2));
        dto.setVenueId(venue.getId());

        mockMvc.perform(
                        post("/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.eventName").value("Test Event"));
    }

    @Test
    void shouldGetAllEvents() throws Exception {
        EventEntity event = new EventEntity();
        event.setEventName("Concert");
        event.setEventDate(LocalDateTime.now().plusDays(1));
        event.setVenue(venue);
        eventJpaRepository.save(event);

        mockMvc.perform(get("/events?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].eventName").value("Concert"));
    }

    @Test
    void shouldGetEventById() throws Exception {
        EventEntity event = new EventEntity();
        event.setEventName("Conference");
        event.setEventDate(LocalDateTime.now().plusDays(1));
        event.setVenue(venue);

        EventEntity saved = eventJpaRepository.save(event);

        mockMvc.perform(get("/events/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventName").value("Conference"));
    }

    @Test
    void shouldReturnNotFoundWhenEventDoesNotExist() throws Exception {
        mockMvc.perform(get("/events/9999"))
                // This will still fail with 500 until we add the exception handler
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteEvent() throws Exception {
        EventEntity event = new EventEntity();
        event.setEventName("To Delete");
        event.setEventDate(LocalDateTime.now().plusDays(1));
        event.setVenue(venue);

        EventEntity saved = eventJpaRepository.save(event);

        mockMvc.perform(delete("/events/" + saved.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFailValidationWhenMissingName() throws Exception {
        RequestEventDTO dto = new RequestEventDTO();
        // Set valid dates so the test only fails for the missing name
        dto.setEventDate(LocalDateTime.now().plusDays(1));
        dto.setEventEndDate(LocalDateTime.now().plusDays(2));
        dto.setVenueId(venue.getId());

        mockMvc.perform(
                        post("/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isBadRequest());
    }
}
