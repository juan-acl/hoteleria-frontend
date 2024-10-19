package com.hoteleria_app.hoteleria_frontend.controller.auth.signUp;

import com.hoteleria_app.hoteleria_frontend.dto.auth.SignUpDto;
import com.hoteleria_app.hoteleria_frontend.service.auth.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    private final AuthService authService;

    public SignUpController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signUp")
    public String signUpComponent() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp
            (
                    @RequestParam String name,
                    @RequestParam String lastname,
                    @RequestParam String email,
                    @RequestParam String password,
                    @RequestParam String phone,
                    HttpSession session
            ) {
        try {

            SignUpDto signUpDto = new SignUpDto(name, lastname, email,
                    password, phone);
            String signUp = authService.signUp(signUpDto);
            if (!signUp.isEmpty()) {
                session.setAttribute("icon", "error");
                session.setAttribute("errorMessage", signUp);
                return "redirect:/signUp";
            }
            session.setAttribute("icon", "success");
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/signUp";
        }
    }
}
