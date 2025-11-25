package com.example.ticketing_system.infrastructure.adapter.out.persistence.repository;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueJpaRepository extends JpaRepository<VenueEntity,Long> {
}
