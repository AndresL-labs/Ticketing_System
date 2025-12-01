package com.example.ticketing_system.infrastructure.adapter.out.persistence.adapter;

import com.example.ticketing_system.domain.model.Role;
import com.example.ticketing_system.domain.port.out.UserRegistrationPort;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRegistrationPort {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity register(String username, String rawPassword, Role role) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .role(role == null ? Role.ROLE_USER : role)
                .build();

        return userRepository.save(user);
    }
}
