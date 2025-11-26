package com.example.ticketing_system.infrastructure.config;

import com.example.ticketing_system.application.usecase.EventUseCase;
import com.example.ticketing_system.application.usecase.VenueUseCase;
import com.example.ticketing_system.domain.port.in.ManageEventUseCase;
import com.example.ticketing_system.domain.port.in.ManageVenueUseCase;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeansConfig {
    @Bean
    ManageVenueUseCase manageVenueUseCase(VenueRepositoryPort venueRepository) {
        return new VenueUseCase(venueRepository);
    }

    @Bean
    ManageEventUseCase manageEventUseCase(EventRepositoryPort eventRepository) {
        return new EventUseCase(eventRepository);
    }
}
