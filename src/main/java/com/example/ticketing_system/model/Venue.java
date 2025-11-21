package com.example.ticketing_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    private Long id;
    private String venueName;
    private String location;
    private int capacity;
}
