package com.example.awsdeploy.dto;

import com.example.awsdeploy.entity.AppUser;

public record LoginResponse (
        String accessToken,
        String tokenType
){
    public static LoginResponse of(String accessToken){
        return new LoginResponse(accessToken, "Bearer");
    }
}
