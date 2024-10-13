package com.hoteleria_app.hoteleria_frontend.controller.home;

import com.hoteleria_app.hoteleria_frontend.dto.hotel.HotelDto;
import com.hoteleria_app.hoteleria_frontend.service.hotel.HotelService;
import com.hoteleria_app.hoteleria_frontend.utils.LoggerUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;

import java.util.Set;

@Controller
public class Home {
    private static final Logger logger = LoggerUtils.createLogger(Home.class);
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    public Home(RestTemplate restTemplate, HotelService hotelService) {
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            Set<HotelDto> hotels = hotelService.getAllHotels();
        } catch (RestClientException e) {
            System.err.println("Error al hacer la petición al API: " + e.getMessage());
        }

        return "home";
    }
}
