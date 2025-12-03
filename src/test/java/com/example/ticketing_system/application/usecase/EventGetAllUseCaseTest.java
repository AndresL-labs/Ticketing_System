package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Event;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EventGetAllUseCaseTest {

    @Test
    void shouldGetAllEvents() {
        // Arrange
        EventRepositoryPort repository = Mockito.mock(EventRepositoryPort.class);
        EventGetAllUseCase useCase = new EventGetAllUseCase(repository);

        List<Event> events = new ArrayList<>();
        events.add(new Event());
        Page<Event> page = new PageImpl<>(events);

        when(repository.findAll(any(), any(), any(), any(), anyInt(), anyInt())).thenReturn(page);

        // Act
        Page<Event> result = useCase.getAllEvents(null, null, null, null, 0, 10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository, times(1)).findAll(any(), any(), any(), any(), anyInt(), anyInt());
    }
}
