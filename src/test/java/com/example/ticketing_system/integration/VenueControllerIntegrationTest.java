package com.example.ticketing_system.integration;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser // Simula un usuario autenticado para todos los tests
class VenueControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VenueJpaRepository venueRepository;

    @BeforeEach
    void setup() {
        // Limpiar la base de datos antes de cada test para asegurar la independencia
        venueRepository.deleteAll();
    }

    private VenueEntity createAndSaveTestVenue() {
        VenueEntity venue = new VenueEntity();
        venue.setVenueName("Initial Venue");
        venue.setCapacity(100);
        venue.setLocation("Initial Location");
        return venueRepository.save(venue);
    }

    @Test
    void shouldCreateVenue() throws Exception {
        String json = """
                {
                    "venueName": "Test Venue",
                    "capacity": 500,
                    "location": "Medellin"
                }
                """;

        mockMvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.venueName", is("Test Venue")));
    }

    @Test
    void shouldGetAllVenues() throws Exception {
        createAndSaveTestVenue();

        mockMvc.perform(get("/venues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", not(empty())));
    }

    @Test
    void shouldGetVenueById() throws Exception {
        VenueEntity savedVenue = createAndSaveTestVenue();

        mockMvc.perform(get("/venues/" + savedVenue.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedVenue.getId().intValue())));
    }

    @Test
    void shouldUpdateVenue() throws Exception {
        VenueEntity savedVenue = createAndSaveTestVenue();

        String json = """
                {
                    "venueName": "Updated Venue",
                    "capacity": 900,
                    "location": "Bogota"
                }
                """;

        mockMvc.perform(put("/venues/" + savedVenue.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.venueName", is("Updated Venue")));
    }

    @Test
    void shouldDeleteVenue() throws Exception {
        VenueEntity savedVenue = createAndSaveTestVenue();

        mockMvc.perform(delete("/venues/" + savedVenue.getId()))
                .andExpect(status().isNoContent());
    }
}
