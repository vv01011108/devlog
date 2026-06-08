package com.example.awsdeploy.dto;

import com.example.awsdeploy.entity.AppUser;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long userId,
        String email,
        String nickname
) {
    public static LoginResponse of(
            String accessToken,
            Long userId,
            String email,
            String nickname){
        return new LoginResponse(
                accessToken,
                "Bearer",
                userId,
                email,
                nickname
        );
    }
}
