package com.hoteleria_app.hoteleria_frontend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseSignInDto {
    public Long status;
    public String message;
    public String token;
}