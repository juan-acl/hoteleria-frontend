package com.hoteleria_app.hoteleria_frontend.controller.reservations;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MyReservations {
    @GetMapping("/myReservations")
    public String MyReservations(Model model) {
        model.addAttribute("reservaciones", new ArrayList<>());
        model.addAttribute("pageContent", "myReservations");
        return "layout";
    }
}
