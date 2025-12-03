package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventGetByIdUseCaseTest {

    @Test
    void shouldGetEventById() {
        // Arrange
        EventRepositoryPort repository = Mockito.mock(EventRepositoryPort.class);
        EventGetByIdUseCase useCase = new EventGetByIdUseCase(repository);

        Event event = new Event();
        event.setId(1L);
        event.setEventName("Test Event");

        when(repository.findById(1L)).thenReturn(event);

        // Act
        Event result = useCase.getEventById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).findById(1L);
    }
}
