package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class VenueDeleteUseCaseTest {

    @Test
    void shouldDeleteVenue() {
        // Arrange
        VenueRepositoryPort repository = Mockito.mock(VenueRepositoryPort.class);
        VenueDeleteUseCase useCase = new VenueDeleteUseCase(repository);
        Long venueId = 1L;

        // Act
        useCase.deleteVenue(venueId);

        // Assert
        verify(repository, times(1)).delete(venueId);
    }
}
