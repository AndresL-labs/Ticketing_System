package com.example.ticketing_system.infrastructure.adapter.out.persistence.repository;

import com.example.ticketing_system.domain.model.User;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}