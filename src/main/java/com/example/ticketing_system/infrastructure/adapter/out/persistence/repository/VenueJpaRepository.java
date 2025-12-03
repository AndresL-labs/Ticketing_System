package com.example.ticketing_system.infrastructure.adapter.out.persistence.repository;

import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.VenueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueJpaRepository extends JpaRepository<VenueEntity,Long> {

    @Query("SELECT v FROM VenueEntity v WHERE v.capacity >= :min")
    Page<VenueEntity> findByCapacity(@Param("min") int min, Pageable pageable);

    @EntityGraph(attributePaths = "events")
    Page<VenueEntity> findAll(Pageable pageable);
}