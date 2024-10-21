package com.hoteleria_app.hoteleria_frontend.controller.hotel;

import com.hoteleria_app.hoteleria_frontend.dto.hotel.HotelDto;
import com.hoteleria_app.hoteleria_frontend.service.hotel.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public String hotels(Model model) {
        try {
            List<HotelDto> hotels = hotelService.getAllHotels();
            model.addAttribute("pageContent", "hotels");
            model.addAttribute("hotels", hotels);
            return "layout";
        } catch (Exception error) {
            model.addAttribute("hotels", List.of());
            return "layout";
        }
    }


}
