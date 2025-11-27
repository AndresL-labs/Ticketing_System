package com.example.ticketing_system.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false)
        private String eventName;
        @Column(nullable = false)
        private LocalDateTime eventDate;
        @ManyToOne
        @JoinColumn(name = "venue_id", nullable = false)
        private VenueEntity venue;
}
