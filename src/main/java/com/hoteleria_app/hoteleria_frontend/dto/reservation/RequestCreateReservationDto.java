package com.hoteleria_app.hoteleria_frontend.dto.reservation;

import lombok.Data;

import java.util.List;

@Data
public class RequestCreateReservationDto {
    public Long id_user;
    public List<RoomReservationDto> room_reservations;
}
