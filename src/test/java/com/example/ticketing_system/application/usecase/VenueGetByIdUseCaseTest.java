package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VenueGetByIdUseCaseTest {

    @Test
    void shouldGetVenueById() {
        // Arrange
        VenueRepositoryPort repository = Mockito.mock(VenueRepositoryPort.class);
        VenueGetByIdUseCase useCase = new VenueGetByIdUseCase(repository);

        Venue venue = new Venue();
        venue.setId(1L);
        venue.setVenueName("Test Venue");

        when(repository.findById(1L)).thenReturn(venue);

        // Act
        Venue result = useCase.getVenueById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).findById(1L);
    }
}
