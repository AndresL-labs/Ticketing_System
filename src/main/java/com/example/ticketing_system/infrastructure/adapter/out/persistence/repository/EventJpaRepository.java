package com.example.ticketing_system.infrastructure.adapter.out.persistence.repository;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJpaRepository extends JpaRepository<EventEntity,Long> {
}
