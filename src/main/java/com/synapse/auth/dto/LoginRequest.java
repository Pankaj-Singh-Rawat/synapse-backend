package com.synapse.auth.dto;

public record LoginRequest(
        String username,
        String password
) {}
