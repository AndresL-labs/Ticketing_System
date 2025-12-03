package com.example.ticketing_system.integration;

import com.example.ticketing_system.config.TestContainerConfig;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.VenueJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestContainerConfig.class) // Usar la configuración centralizada
class VenuePersistenceTest {

    @Autowired
    private VenueJpaRepository venueJpaRepository;

    @BeforeEach
    void setup() {
        // Limpiar el repositorio antes de cada test para asegurar la independencia
        venueJpaRepository.deleteAll();
    }

    @Test
    void shouldPersistVenueSuccessfully() {
        // Arrange
        VenueEntity entity = new VenueEntity();
        entity.setVenueName("Test Venue");
        entity.setCapacity(150);
        entity.setLocation("Medellín");

        // Act
        VenueEntity saved = venueJpaRepository.save(entity);

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getVenueName()).isEqualTo("Test Venue");
        assertThat(saved.getCapacity()).isEqualTo(150);
        assertThat(saved.getLocation()).isEqualTo("Medellín");
    }
}
