package com.hoteleria_app.hoteleria_frontend.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResponseGetAllHotelsDto {
    public int status;
    public String message;
    public int count;
    public HotelDto[] hotels;
}
