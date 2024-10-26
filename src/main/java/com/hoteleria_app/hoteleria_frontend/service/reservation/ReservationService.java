package com.hoteleria_app.hoteleria_frontend.service.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteleria_app.hoteleria_frontend.config.JWTEception;
import com.hoteleria_app.hoteleria_frontend.dto.auth.UserProfileDto;
import com.hoteleria_app.hoteleria_frontend.dto.reservation.RequestCreateReservationDto;
import com.hoteleria_app.hoteleria_frontend.dto.reservation.ResponseReservationDto;
import com.hoteleria_app.hoteleria_frontend.dto.reservation.RoomReservationDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/reservation";
    public final RestTemplate restTemplate;
    private final HttpServletRequest request;
    ObjectMapper objectMapper = new ObjectMapper();

    public ReservationService(HttpServletRequest request,
                              RestTemplate restTemplate) {
        this.request = request;
        this.restTemplate = restTemplate;
    }

    public UserProfileDto getProfile() {
        String token = (String) request.getSession().getAttribute("token");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(BASE_URL + "/user/me",
                            HttpMethod.GET,
                            entity,
                            String.class);


            if (!response.getStatusCode().is2xxSuccessful()) {
                return null;
            }
            UserProfileDto userProfileDto =
                    objectMapper.readValue(response.getBody(),
                            UserProfileDto.class);
            return userProfileDto;
        } catch (Exception e) {
            throw new JWTEception("JWT expired");
        }
    }

    public String createReservation(
            LocalDateTime initialDate,
            LocalDateTime finalDate,
            Long id_room
    ) {
        try {
            String token = (String) request.getSession().getAttribute("token");
            System.out.println("Veamos el token si esta bniemo" + token);
            HttpHeaders headers = new HttpHeaders();
            //headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            RoomReservationDto roomReservationDto = new RoomReservationDto();
            roomReservationDto.setInitial_reservation_date(initialDate);
            roomReservationDto.setFinal_reservation_date(finalDate);
            roomReservationDto.setId_room(id_room);

            RequestCreateReservationDto requestCreateReservationDto =
                    new RequestCreateReservationDto();
            requestCreateReservationDto.setId_user(2l);
            requestCreateReservationDto.setRoom_reservations(List.of(roomReservationDto));

            HttpEntity<RequestCreateReservationDto> entity =
                    new HttpEntity<>(requestCreateReservationDto, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    BASE_URL + CURRENT_PATH + "/createReservation",
                    entity,
                    String.class
            );

            ResponseReservationDto responseReservationDto =
                    objectMapper.readValue(response.getBody(),
                            ResponseReservationDto.class);

            if (responseReservationDto.getStatus().equals("success")) {
                return "Reservación creada con éxito";
            }
            return responseReservationDto.getMessage();
        } catch (JWTEception error) {
            request.getSession().removeAttribute("token");
            return "Error al crear la reservación";
        } catch (Exception e) {
            System.out.println("Error en la reserva: " + e.getMessage());
            return "Error al crear la reservación";
        }
    }

}
