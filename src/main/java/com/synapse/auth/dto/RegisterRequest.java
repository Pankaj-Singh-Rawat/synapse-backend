package com.synapse.auth.dto;

public record RegisterRequest(
        String username,
        String password
) {}
