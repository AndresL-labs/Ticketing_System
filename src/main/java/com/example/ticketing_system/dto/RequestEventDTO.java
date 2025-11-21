package com.example.ticketing_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventDTO {
    private String eventName;
    private LocalDateTime eventDate;
    private Long venueId;
}
