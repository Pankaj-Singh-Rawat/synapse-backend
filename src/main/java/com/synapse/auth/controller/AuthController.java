package com.synapse.auth.controller;

import com.synapse.auth.dto.LoginRequest;
import com.synapse.auth.security.jwt.JwtUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        String username = request.username();
        String role = "USER";
        return jwtUtil.generateToken(username, role);
    }
}
