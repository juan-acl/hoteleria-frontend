package com.hoteleria_app.hoteleria_frontend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SignUpDto {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String phone;

}
