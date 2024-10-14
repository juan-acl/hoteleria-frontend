package com.hoteleria_app.hoteleria_frontend.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteleria_app.hoteleria_frontend.dto.auth.ResponseSignInDto;
import com.hoteleria_app.hoteleria_frontend.dto.auth.SignInDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;

    ObjectMapper objectMapper = new ObjectMapper();
    public final String CURRENT_PATH = "/auth";
    public final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String signIn(SignInDto user) {
        try {
            ResponseEntity<String> responseSignIn =
                    restTemplate.postForEntity(BASE_URL + CURRENT_PATH +
                                    "/signIn",
                            user, String.class);
            ResponseSignInDto responseSignInDto =
                    objectMapper.readValue(responseSignIn.getBody(),
                            ResponseSignInDto.class);
            if (responseSignInDto.getToken() == null) {
                return "";
            }
            return responseSignInDto.getToken();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

}
