package com.hoteleria_app.hoteleria_frontend.controller.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hoteleria_app.hoteleria_frontend.config.JWTEception;
import com.hoteleria_app.hoteleria_frontend.dto.auth.UserProfileDto;
import com.hoteleria_app.hoteleria_frontend.service.reservation.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

@Controller
public class ProfileController {
    @Value("${hotel.service.backend.url}")
    public String BASE_URL;
    public final String CURRENT_PATH = "/user/me";
    public final RestTemplate restTemplate;
    private final HttpServletRequest request;
    private ReservationService reservationService;
    private final ObjectMapper objectMapper;

    public ProfileController(
            HttpServletRequest request,
            RestTemplate restTemplate
    ) {
        this.request = request;
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @GetMapping("/me")
    public String profile(Model model) {
        String token = (String) request.getSession().getAttribute("token");
        if (token == null) {
            return "redirect:/signIn";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(BASE_URL + CURRENT_PATH,
                            HttpMethod.GET,
                            entity,
                            String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                UserProfileDto userProfileDto =
                        objectMapper.readValue(response.getBody(),
                                UserProfileDto.class);
                model.addAttribute("userProfile",
                        userProfileDto.getCurrentUser());
            } else {
                model.addAttribute("error", "Error fetching user profile");
            }
            model.addAttribute("pageContent", "me");
            return "layout";
        } catch (JWTEception jwtException) {
            request.getSession().removeAttribute("token");
            return "redirect:/signIn";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            model.addAttribute("pageContent", "me");
            String errorMessage = e.getMessage();
            System.out.println("Error: " + errorMessage);

            // Verifica si el mensaje de error indica que el token ha expirado
            if (errorMessage != null && errorMessage.contains("JWT expired")) {
                request.getSession().removeAttribute("token");
                return "redirect:/signIn";
            } else {
                model.addAttribute("error",
                        "An error occurred: " + errorMessage);
            }
            return "redirect:/signIn";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        request.getSession().removeAttribute("token");
        return "redirect:/signIn";
    }
}
