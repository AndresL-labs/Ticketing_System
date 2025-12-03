package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class EventDeleteUseCaseTest {

    @Test
    void shouldDeleteEvent() {
        // Arrange
        EventRepositoryPort repository = Mockito.mock(EventRepositoryPort.class);
        EventDeleteUseCase useCase = new EventDeleteUseCase(repository);
        Long eventId = 1L;

        // Act
        useCase.deleteEvent(eventId);

        // Assert
        verify(repository, times(1)).delete(eventId);
    }
}
