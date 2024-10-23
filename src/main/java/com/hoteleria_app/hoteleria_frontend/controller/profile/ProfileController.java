package com.hoteleria_app.hoteleria_frontend.controller.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoteleria_app.hoteleria_frontend.dto.auth.UserProfileDto;
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
    ObjectMapper objectMapper = new ObjectMapper();


    public ProfileController(HttpServletRequest request,
                             RestTemplate restTemplate) {
        this.request = request;
        this.restTemplate = restTemplate;
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
                System.out.println("Response:sasdasdadad " + userProfileDto.getCurrentUser());
                model.addAttribute("userProfile",
                        userProfileDto.getCurrentUser());
            } else {
                model.addAttribute("error", "Error fetching user profile");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "An error occurred: " + e.getMessage());
        }

        model.addAttribute("pageContent", "me");
        return "layout";
    }

    @GetMapping("/logout")
    public String logout() {
        request.getSession().removeAttribute("token");
        return "redirect:/signIn";
    }
}
