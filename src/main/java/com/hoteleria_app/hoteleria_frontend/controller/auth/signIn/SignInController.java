package com.hoteleria_app.hoteleria_frontend.controller.auth.signIn;

import com.hoteleria_app.hoteleria_frontend.dto.auth.SignInDto;
import com.hoteleria_app.hoteleria_frontend.service.auth.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInController {
    private final AuthService authService;

    public SignInController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signIn")
    public String signInComponent() {
        return "signIn";
    }

    @PostMapping("/signIn")
    public String signIn(
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes model
    ) {
        try {
            SignInDto userSignIn = new SignInDto(email, password);
            String signIn = authService.signIn(userSignIn);
            if (signIn.isEmpty()) {
                model.addFlashAttribute("icon", "error");
                model.addFlashAttribute("errorMessage", "Invalid" +
                        " " +
                        "credentials. " +
                        "Please try " +
                        "again.");
                return "redirect:/signIn";
            }
            model.addFlashAttribute("icon", "success");
            return "redirect:/";
        } catch (Exception e) {
            model.addFlashAttribute("icon", "error");
            model.addFlashAttribute("errorMessage", "An error " +
                    "occurred: ");
            return "signIn";
        }
    }

}
