package com.hoteleria_app.hoteleria_frontend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseSignUpDto<T> {
    private String status;
    private String message;
    private Integer count;
    private T user;
}
