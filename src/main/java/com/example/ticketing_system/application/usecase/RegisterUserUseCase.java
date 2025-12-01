package com.example.ticketing_system.application.usecase;

import com.example.ticketing_system.domain.model.Role;
import com.example.ticketing_system.domain.port.out.UserRegistrationPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRegistrationPort userRegistrationPort;

    public UserEntity register(String username, String password, Role role) {
        return userRegistrationPort.register(username, password, role);
    }
}

