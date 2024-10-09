package com.hoteleria_app.hoteleria_frontend.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class Home {
    private final RestTemplate restTemplate;

    public Home(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String home(Model model) {
        String url_api = "http://localhost:9090/api/hotel/getAllHotels";

        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(null);

            ResponseEntity<String> response = restTemplate.postForEntity(url_api, requestEntity, String.class);
            model.addAllAttributes(response.getHeaders());
            String responseData = response.getBody();
            System.out.println("Response from API: " + responseData);
        } catch (RestClientException e) {

            System.err.println("Error al hacer la petición al API: " + e.getMessage());
        }

        return "home";
    }
}
