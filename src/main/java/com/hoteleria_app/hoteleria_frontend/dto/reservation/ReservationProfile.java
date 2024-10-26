package com.hoteleria_app.hoteleria_frontend.dto.reservation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationProfile {
    private Long id;
    private LocalDateTime emitionDate;
    private String status;
    private Double total;
}
