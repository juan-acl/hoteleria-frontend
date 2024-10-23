package com.hoteleria_app.hoteleria_frontend.dto;

import com.hoteleria_app.hoteleria_frontend.dto.hotel.CreateHotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateHotelDto extends CreateHotel {
    public Long id_hotel;
}
