package com.example.ticketing_system.infrastructure.adapter.in.web;

import com.example.ticketing_system.application.usecase.RegisterUserUseCase;
import com.example.ticketing_system.domain.model.Role;
import com.example.ticketing_system.infrastructure.adapter.in.dto.AuthRequest;
import com.example.ticketing_system.infrastructure.adapter.in.dto.AuthResponse;
import com.example.ticketing_system.infrastructure.adapter.in.dto.RegisterRequest;
import com.example.ticketing_system.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.example.ticketing_system.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        Role role = null;
        if (req.getRole() != null) {
            try { role = Role.valueOf(req.getRole()); } catch (Exception ignored){}
        }
        UserEntity saved = userService.register(req.getUsername(), req.getPassword(), role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(ud.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
