package com.hoteleria_app.hoteleria_frontend.controller.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {
    @GetMapping("/roomsByHotel")
    public String roomsByHotel() {
//        return "roomsByHotel";
        return "layout";
    }
}
