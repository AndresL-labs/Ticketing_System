package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VenueCreateUseCaseTest {

    @Test
    void shouldCreateVenue() {
        // Arrange
        VenueRepositoryPort repository = Mockito.mock(VenueRepositoryPort.class);
        VenueCreateUseCase useCase = new VenueCreateUseCase(repository);

        Venue inputVenue = new Venue();
        inputVenue.setVenueName("Test Venue");

        Venue savedVenue = new Venue();
        savedVenue.setId(1L);
        savedVenue.setVenueName("Test Venue");

        when(repository.save(inputVenue)).thenReturn(savedVenue);

        // Act
        Venue result = useCase.createVenue(inputVenue);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(inputVenue);
    }
}
