package com.example.ticketing_system.infrastructure.adapter.in.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
