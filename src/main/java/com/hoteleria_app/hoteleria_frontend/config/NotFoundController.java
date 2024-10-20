package com.hoteleria_app.hoteleria_frontend.config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotFoundController implements ErrorController {

    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("pageContent", "notFound");
        return "layout";
    }

}
