package com.hoteleria_app.hoteleria_frontend.dto.room;

import lombok.Data;

@Data
public class RoomsDto {
    private int id;
    private int roomNumber;
    private boolean available;
    private String name;
    private boolean active;
    private double price;
    private RoomTypeDto idRoomType;
    private HotelDto idHotel;

    @Data
    public static class RoomTypeDto {
        private int idRoomType;
        private String name;
        private String description;
        private String amenities;
        private int maximumPeople;
    }

    @Data
    public static class HotelDto {
        private int idHotel;
        private String name;
        private String email;
        private String address;
        private String phone;
        private String description;
        private double rating;
        private boolean status;
    }
}
