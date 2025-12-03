package com.example.ticketing_system.domain.service;

import com.example.ticketing_system.domain.model.Event;

import java.time.LocalDate;

public class EventDateValidation {
    public static boolean validate(Event event){
        return !event.getEventDate().isBefore(LocalDate.now().atStartOfDay());
    }
}