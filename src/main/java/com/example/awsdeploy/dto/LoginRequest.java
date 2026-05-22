package com.example.awsdeploy.dto;

public record LoginRequest (
        String email,
        String password
) {}
