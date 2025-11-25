package com.example.ticketing_system.infrastructure.adapter.in.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventDTO {
    @NotBlank
    @Size(min = 1, max = 100)
    private String eventName;
    @NotNull
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDateTime eventDate;
    @NotNull
    private Long venueId;
}
