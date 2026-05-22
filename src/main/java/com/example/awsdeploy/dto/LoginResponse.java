package com.example.awsdeploy.dto;

import com.example.awsdeploy.entity.AppUser;

public record LoginResponse (
        Long id,
        String email,
        String nickname,
        String message
){
    public static LoginResponse from(AppUser user){
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                "로그인 성공"
        );
    }
}
