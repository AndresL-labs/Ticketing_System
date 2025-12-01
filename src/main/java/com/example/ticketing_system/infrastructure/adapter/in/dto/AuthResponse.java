package com.example.ticketing_system.infrastructure.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    private final String token;
    private String tokenType = "Bearer";
}
