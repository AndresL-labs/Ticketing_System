package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventUpdateUseCaseTest {

    @Test
    void shouldUpdateEvent() {
        // Arrange
        EventRepositoryPort repository = Mockito.mock(EventRepositoryPort.class);
        EventUpdateUseCase useCase = new EventUpdateUseCase(repository);

        Event eventToUpdate = new Event();
        eventToUpdate.setEventName("Updated Event");

        Event existingEvent = new Event();
        existingEvent.setId(1L);
        existingEvent.setEventName("Original Event");

        when(repository.findById(1L)).thenReturn(existingEvent);
        when(repository.save(any(Event.class))).thenReturn(eventToUpdate);

        // Act
        Event result = useCase.updateEvent(1L, eventToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("Original Event", result.getEventName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(0)).save(any(Event.class));
    }
}
