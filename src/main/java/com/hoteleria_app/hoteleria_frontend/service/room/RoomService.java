package com.hoteleria_app.hoteleria_frontend.service.room;

import com.hoteleria_app.hoteleria_frontend.config.JWTEception;
import com.hoteleria_app.hoteleria_frontend.dto.room.GetRoomsByIdHotel;
import com.hoteleria_app.hoteleria_frontend.dto.room.RoomsDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoomService {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/room";
    public final RestTemplate restTemplate;
    private final HttpServletRequest request;

    public RoomService(HttpServletRequest request, RestTemplate restTemplate) {
        this.request = request;
        this.restTemplate = restTemplate;
    }

    public List<RoomsDto> getRoomsByHotel(Long id_hotel) {
        try {
            String token = (String) request.getSession().getAttribute("token");
            System.out.println("TOKEN: " + token + " ID_HOTEL: " + id_hotel);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            GetRoomsByIdHotel getRoomsByIdHotel = new GetRoomsByIdHotel();
            getRoomsByIdHotel.setId_hotel(id_hotel);

            // Cambia aquí para usar ResponseEntity<List<RoomsDto>>
            ResponseEntity<List<RoomsDto>> response =
                    restTemplate.exchange(BASE_URL + CURRENT_PATH +
                                    "/getRoomsByHotelId",
                            HttpMethod.POST,
                            new HttpEntity<>(getRoomsByIdHotel, headers),
                            new ParameterizedTypeReference<List<RoomsDto>>() {
                            });

            // Maneja la respuesta
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.out.println("Error en la respuesta: " + response.getStatusCode());
                return List.of();
            }
        } catch (HttpClientErrorException e) {
            System.out.println("ESTE ES EL ERROR: " + e.getMessage());
            if (e.getStatusCode() == HttpStatus.FORBIDDEN && e.getResponseBodyAsString().contains("JWT expired")) {
                throw new JWTEception("JWT expired");
            }
            return List.of();
        }
    }

}
