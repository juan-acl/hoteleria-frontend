package com.hoteleria_app.hoteleria_frontend.controller.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    @GetMapping("/hotels")
    
    public String hotels(Model model) {
        model.addAttribute("pageContent", "hotels");
        return "layout";
    }
}
