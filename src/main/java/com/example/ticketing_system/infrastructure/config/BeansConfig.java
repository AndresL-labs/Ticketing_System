package com.example.ticketing_system.infrastructure.config;

import com.example.ticketing_system.application.usecase.*;
import com.example.ticketing_system.domain.port.in.*;
import com.example.ticketing_system.domain.port.out.EventRepositoryPort;
import com.example.ticketing_system.domain.port.out.UserRegistrationPort;
import com.example.ticketing_system.domain.port.out.VenueRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeansConfig {
    @Bean
    IEventCreateUseCase eventCreateUseCase(EventRepositoryPort eventRepository) {
        return new EventCreateUseCase(eventRepository);
    }

    @Bean
    IEventDeleteUseCase eventDeleteUseCase(EventRepositoryPort eventRepository) {
        return new EventDeleteUseCase(eventRepository);
    }

    @Bean
    IEventGetAllUseCase eventGetAllUseCase(EventRepositoryPort eventRepository) {
        return new EventGetAllUseCase(eventRepository);
    }

    @Bean
    IEventGetByIdUseCase eventGetByIdUseCase(EventRepositoryPort eventRepository) {
        return new EventGetByIdUseCase(eventRepository);
    }

    @Bean
    IEventUpdateUseCase eventUpdateUseCase(EventRepositoryPort eventRepository) {
        return new EventUpdateUseCase(eventRepository);
    }

    @Bean
    IVenueCreateUseCase venueCreateUseCase(VenueRepositoryPort venueRepository) {
        return new VenueCreateUseCase(venueRepository);
    }

    @Bean
    IVenueDeleteUseCase venueDeleteUseCase(VenueRepositoryPort venueRepository) {
        return new VenueDeleteUseCase(venueRepository);
    }

    @Bean
    IVenueGetAllUseCase venueGetAllUseCase(VenueRepositoryPort venueRepository) {
        return new VenueGetAllUseCase(venueRepository);
    }

    @Bean
    IVenueGetByIdUseCase venueGetByIdUseCase(VenueRepositoryPort venueRepository) {
        return new VenueGetByIdUseCase(venueRepository);
    }

    @Bean
    IVenueUpdateUseCase venueUpdateUseCase(VenueRepositoryPort venueRepository) {
        return new VenueUpdateUseCase(venueRepository);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRegistrationPort port) {
        return new RegisterUserUseCase(port);
    }


}
