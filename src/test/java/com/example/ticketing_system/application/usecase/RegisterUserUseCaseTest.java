package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Role;
import com.example.ticketing_system.domain.port.out.UserRegistrationPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegisterUserUseCaseTest {

    @Test
    void shouldRegisterUser() {
        // Arrange
        UserRegistrationPort repository = Mockito.mock(UserRegistrationPort.class);
        RegisterUserUseCase useCase = new RegisterUserUseCase(repository);

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testuser");

        when(repository.register("testuser", "password", Role.ROLE_USER)).thenReturn(user);

        // Act
        UserEntity result = useCase.register("testuser", "password", Role.ROLE_USER);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).register("testuser", "password", Role.ROLE_USER);
    }
}
