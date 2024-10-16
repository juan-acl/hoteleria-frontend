package com.hoteleria_app.hoteleria_frontend.controller.auth.signUp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @GetMapping("/signUp")
    public String signUpComponent() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
}
