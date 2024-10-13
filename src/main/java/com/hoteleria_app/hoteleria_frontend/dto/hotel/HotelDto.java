package com.hoteleria_app.hoteleria_frontend.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class HotelDto {
    public Long id_hotel;
    public String name;
    public String email;
    public String phone;
    public String description;
    public String address;
    public Double rating;
    public Integer status;
}
