package com.example.ticketing_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVenueDTO {
    private Long id;
    private String venueName;
    private String location;
    private int capacity;
}
