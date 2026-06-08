package com.example.awsdeploy.dto;

public record ErrorResponse (
        int status,
        String message
){
}
