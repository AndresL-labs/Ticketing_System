package com.example.ticketing_system.domain.model;

public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public User() {}

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}


