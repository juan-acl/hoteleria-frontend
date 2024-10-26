package com.hoteleria_app.hoteleria_frontend.config;

public class JWTEception extends RuntimeException {
    public JWTEception(String message) {
        super(message);
    }
}
