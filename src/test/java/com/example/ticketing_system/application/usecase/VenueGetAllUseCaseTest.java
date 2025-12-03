package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Venue;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class VenueGetAllUseCaseTest {

    @Test
    void shouldGetAllVenues() {
        // Arrange
        VenueRepositoryPort repository = Mockito.mock(VenueRepositoryPort.class);
        VenueGetAllUseCase useCase = new VenueGetAllUseCase(repository);

        List<Venue> venues = new ArrayList<>();
        venues.add(new Venue());
        Page<Venue> page = new PageImpl<>(venues);

        when(repository.findAll(any(), any(Pageable.class))).thenReturn(page);

        // Act
        Page<Venue> result = useCase.getAllVenues(null, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(any(), any(Pageable.class));
    }
}
