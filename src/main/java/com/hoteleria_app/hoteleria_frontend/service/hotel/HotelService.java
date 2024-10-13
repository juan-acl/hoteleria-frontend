package com.hoteleria_app.hoteleria_frontend.service.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteleria_app.hoteleria_frontend.dto.hotel.HotelDto;
import com.hoteleria_app.hoteleria_frontend.dto.hotel.ResponseGetAllHotelsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;


@Service
public class HotelService {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/hotel";
    public final RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public HotelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<HotelDto> getAllHotels() {
        try {
            ResponseEntity<String> response =
                    restTemplate.postForEntity(BASE_URL + CURRENT_PATH +
                                    "/getAllHotels",
                            null, String.class);

            ResponseGetAllHotelsDto responseGetAllHotelsDto =
                    objectMapper.readValue(response.getBody(),
                            ResponseGetAllHotelsDto.class);
            if(responseGetAllHotelsDto.getStatus() != 200) {
                return null;
            }
            return Set.of(responseGetAllHotelsDto.getHotels());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
