package com.example.ticketing_system.domain.port.out;

import com.example.ticketing_system.domain.model.Role;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;

public interface UserRegistrationPort {
    UserEntity register(String username, String password, Role role);
}