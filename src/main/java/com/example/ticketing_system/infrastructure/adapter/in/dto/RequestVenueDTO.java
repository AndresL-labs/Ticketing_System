package com.example.ticketing_system.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVenueDTO {
    @NotBlank
    private String venueName;
    @NotNull
    private String location;
    @NotNull
    @Positive(message = "La capacidad debe ser un valor positivo mayor a cero")
    private int capacity;
}
