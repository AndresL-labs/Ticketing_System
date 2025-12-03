package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventCreateUseCaseTest {

    @Test
    void shouldCreateEvent() {
        // Arrange
        EventRepositoryPort repository = Mockito.mock(EventRepositoryPort.class);
        EventCreateUseCase useCase = new EventCreateUseCase(repository);

        Event inputEvent = new Event();
        inputEvent.setEventName("Test Event");

        Event savedEvent = new Event();
        savedEvent.setId(1L);
        savedEvent.setEventName("Test Event");

        when(repository.save(inputEvent)).thenReturn(savedEvent);

        // Act
        Event result = useCase.createEvent(inputEvent);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(inputEvent);
    }
}
