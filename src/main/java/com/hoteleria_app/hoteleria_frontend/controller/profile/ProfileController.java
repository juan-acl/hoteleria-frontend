package com.hoteleria_app.hoteleria_frontend.controller.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/me")
    public String profile(Model model) {
        model.addAttribute("pageContent", "me");
        return "layout";
    }
}
