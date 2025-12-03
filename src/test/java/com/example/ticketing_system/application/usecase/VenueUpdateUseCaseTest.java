package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VenueUpdateUseCaseTest {

    @Test
    void shouldUpdateVenue() {
        // Arrange
        VenueRepositoryPort repository = Mockito.mock(VenueRepositoryPort.class);
        VenueUpdateUseCase useCase = new VenueUpdateUseCase(repository);

        Venue venueToUpdate = new Venue();
        venueToUpdate.setVenueName("Updated Venue");

        Venue updatedVenue = new Venue();
        updatedVenue.setId(1L);
        updatedVenue.setVenueName("Updated Venue");

        when(repository.update(1L, venueToUpdate)).thenReturn(updatedVenue);

        // Act
        Venue result = useCase.updateVenue(1L, venueToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Venue", result.getVenueName());
        verify(repository, times(1)).update(1L, venueToUpdate);
    }
}
