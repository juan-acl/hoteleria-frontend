package com.hoteleria_app.hoteleria_frontend.controller.reservations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hoteleria_app.hoteleria_frontend.dto.auth.UserProfileDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
public class MyReservations {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/user/me";
    public final RestTemplate restTemplate;
    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;


    public MyReservations(
            HttpServletRequest request,
            RestTemplate restTemplate
    ) {
        this.request = request;
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @GetMapping("/myReservations")
    public String MyReservations(Model model) {
        String token = (String) request.getSession().getAttribute("token");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        try {
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response =
                    restTemplate.exchange(BASE_URL + CURRENT_PATH,
                            HttpMethod.GET,
                            entity,
                            String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                UserProfileDto userProfileDto =
                        objectMapper.readValue(response.getBody(),
                                UserProfileDto.class);
                System.out.println("Viendo los registro s" + userProfileDto.getCurrentUser().getReservations().size());
                model.addAttribute("reservaciones",
                        userProfileDto.getCurrentUser().getReservations());
            } else {
                model.addAttribute("error", "Error fetching user profile");
            }
            model.addAttribute("pageContent", "myReservations");
            return "layout";
        } catch (Exception e) {
            model.addAttribute("reservaciones", new ArrayList<>());
            model.addAttribute("pageContent", "myReservations");
            return "layout";
        }
    }
}
