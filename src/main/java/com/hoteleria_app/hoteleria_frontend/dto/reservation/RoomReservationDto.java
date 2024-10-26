package com.hoteleria_app.hoteleria_frontend.dto.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomReservationDto {
    public LocalDateTime initial_reservation_date;
    public LocalDateTime final_reservation_date;
    public Long id_room;
}
