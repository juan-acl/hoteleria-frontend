package com.hoteleria_app.hoteleria_frontend.controller.auth.signIn;

import com.hoteleria_app.hoteleria_frontend.dto.auth.ResponseToken;
import com.hoteleria_app.hoteleria_frontend.dto.auth.SignInDto;
import com.hoteleria_app.hoteleria_frontend.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {
    private final AuthService authService;
    private final HttpSession session;

    public SignInController(AuthService authService, HttpSession session) {
        this.authService = authService;
        this.session = session;
    }

    @GetMapping("/signIn")
    public String signInComponent(Model model) {
        session.removeAttribute("errorMessage");
        session.removeAttribute("icon");
        model.addAttribute("pageContent", "signIn");
        return "layout";
    }

    @PostMapping("/signIn")
    public String signIn(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request
    ) {
        try {
            SignInDto userSignIn = new SignInDto(email, password);
            ResponseToken token = authService.signIn(userSignIn);
            if (token.getToken() == null) {
                session.setAttribute("icon", "error");
                session.setAttribute("errorMessage", token.getMessage());
                return "redirect:/signIn";
            }
            request.getSession().setAttribute("token", token.getToken());
            session.setAttribute("icon", "success");
            return "redirect:/";
        } catch (Exception e) {
            session.setAttribute("icon", "error");
            session.setAttribute("errorMessage", "An error occurred: ");
            return "signIn";
        }
    }

    @PostMapping("/clearSession")
    public void clearSession(HttpSession session) {
        session.removeAttribute("errorMessage");
        session.removeAttribute("icon");
    }

}
