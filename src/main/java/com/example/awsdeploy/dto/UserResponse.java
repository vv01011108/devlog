package com.example.awsdeploy.dto;

import com.example.awsdeploy.entity.AppUser;

public record UserResponse (
        Long id,
        String email,
        String nickname
){
    public static UserResponse from(AppUser user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
