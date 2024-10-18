package com.hoteleria_app.hoteleria_frontend.controller.auth.signIn;

import com.hoteleria_app.hoteleria_frontend.dto.auth.ResponseToken;
import com.hoteleria_app.hoteleria_frontend.dto.auth.SignInDto;
import com.hoteleria_app.hoteleria_frontend.service.auth.AuthService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInController {
    private final AuthService authService;
    private final HttpSession session;

    public SignInController(AuthService authService, HttpSession session) {
        this.authService = authService;
        this.session = session;
    }

    @GetMapping("/signIn")
    public String signInComponent() {
        session.removeAttribute("errorMessage");
        session.removeAttribute("icon");
        return "signIn";
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
