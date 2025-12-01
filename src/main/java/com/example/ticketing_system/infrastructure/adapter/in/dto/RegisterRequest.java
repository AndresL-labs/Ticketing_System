package com.example.ticketing_system.infrastructure.adapter.in.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role; // "ROLE_USER" o "ROLE_ADMIN" (opcional)
}
