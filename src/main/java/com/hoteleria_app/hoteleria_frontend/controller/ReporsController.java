package com.hoteleria_app.hoteleria_frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporsController {

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("pageContent", "reports");
        return "layout";
    }
}
