package com.hoteleria_app.hoteleria_frontend.service.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteleria_app.hoteleria_frontend.dto.hotel.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.List;


@Service
public class HotelService {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/hotel";
    public final RestTemplate restTemplate;
    private final HttpServletRequest request;

    ObjectMapper objectMapper = new ObjectMapper();

    public HotelService(RestTemplate restTemplate, HttpServletRequest request) {
        this.restTemplate = restTemplate;
        this.request = request;
    }

    public boolean deleteHotel(Long id_hotel) {
        try {
            System.out.println("id_hotel service" + id_hotel);
            String token = (String) request.getSession().getAttribute("token");
            System.out.println("este es el etoekn" + token);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            DeleteHotelDto hotelDelete = new DeleteHotelDto(id_hotel);
            HttpEntity<DeleteHotelDto> requestEntity =
                    new HttpEntity<>(hotelDelete,
                    headers);
            ResponseEntity<String> response = restTemplate.postForEntity(
                    BASE_URL + CURRENT_PATH + "/deleteHotel", requestEntity,
                    String.class);

            System.out.println("response" + response);
            ResponseCreateHotel responseGetAllHotelsDto =
                    objectMapper.readValue(response.getBody(),
                            ResponseCreateHotel.class);
            System.out.println("validando lso valores de respuesta" + responseGetAllHotelsDto.getStatus());
            if (responseGetAllHotelsDto.getStatus() != 200) {
                return false;
            }
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    public boolean createHotel(
            CreateHotel hotel
    ) {
        try {
            String token = (String) request.getSession().getAttribute("token");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(BASE_URL + CURRENT_PATH +
                            "/createHotel", hotel, String.class);

            ResponseCreateHotel responseGetAllHotelsDto =
                    objectMapper.readValue(response.getBody(),
                            ResponseCreateHotel.class);

            if (responseGetAllHotelsDto.getStatus() != 200) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<HotelDto> getAllHotels() {
        try {
            String token = (String) request.getSession().getAttribute("token");
            ResponseEntity<String> response =
                    restTemplate.postForEntity(BASE_URL + CURRENT_PATH +
                            "/getAllHotels", null, String.class);

            ResponseGetAllHotelsDto responseGetAllHotelsDto =
                    objectMapper.readValue(response.getBody(),
                            ResponseGetAllHotelsDto.class);

            if (responseGetAllHotelsDto.getStatus() != 200) {
                return List.of();
            }
            return responseGetAllHotelsDto.getHotels();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }


}
