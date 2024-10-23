package com.hoteleria_app.hoteleria_frontend.controller.hotel;

import com.hoteleria_app.hoteleria_frontend.dto.UpdateHotelDto;
import com.hoteleria_app.hoteleria_frontend.dto.hotel.CreateHotel;
import com.hoteleria_app.hoteleria_frontend.dto.hotel.HotelDto;
import com.hoteleria_app.hoteleria_frontend.service.hotel.HotelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelController {
    private final HotelService hotelService;
    private final HttpSession session;

    public HotelController(HotelService hotelService, HttpSession session) {
        this.hotelService = hotelService;
        this.session = session;
    }

    @GetMapping("/hotels")
    public String hotels(Model model) {
        try {
            List<HotelDto> hotels = hotelService.getAllHotels();
            model.addAttribute("pageContent", "hotels");
            model.addAttribute("hotels", hotels);
            return "layout";
        } catch (Exception error) {
            model.addAttribute("hotels", List.of());
            return "layout";
        }
    }

    @PostMapping("/hotels")
    public String hotels(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String description,
            @RequestParam Double rating,
            Model model
    ) {
        try {
            CreateHotel newHotel = new CreateHotel(name, email, phone,
                    description, address, rating, 1);
            boolean response = hotelService.createHotel(newHotel);
            if (!response) {
                session.setAttribute("icon", "error");
                session.setAttribute("errorMessage", "Error al crear el hotel");
            }
            session.setAttribute("icon", "success");
            session.setAttribute("errorMessage", "Hotel creado correctamente");
            List<HotelDto> hotels = hotelService.getAllHotels();
            model.addAttribute("pageContent", "hotels");
            model.addAttribute("hotels", hotels);
            return "layout";
        } catch (Exception error) {
            System.out.println("ERRROR" + error.getMessage());
            model.addAttribute("hotels", List.of());
            return "layout";
        }
    }

    @PostMapping("/deleteHotel")
    public String deleteHotel(
            @RequestParam Long id_hotel,
            Model model
    ) {
        try {
            System.out.println("ID HOTEL" + id_hotel);
            boolean response = hotelService.deleteHotel(id_hotel);
            if (!response) {
                session.setAttribute("icon", "error");
                session.setAttribute("errorMessage", "Error al eliminar el " +
                        "hotel");
            }
            session.setAttribute("icon", "success");
            session.setAttribute("errorMessage", "Hotel eliminado " +
                    "correctamente");
            List<HotelDto> hotels = hotelService.getAllHotels();
            model.addAttribute("pageContent", "hotels");
            model.addAttribute("hotels", hotels);
            return "redirect:/hotels";
        } catch (Exception error) {
            System.out.println("ERRROR" + error.getMessage());
            model.addAttribute("hotels", List.of());
            return "layout";
        }
    }

    @PostMapping("/updateHotel")
    public String updateHotel(
            @RequestParam Long id_hotel,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String description,
            @RequestParam Double rating,
            Model model
    ) {
        try {
            UpdateHotelDto updateHotel = new UpdateHotelDto();
            updateHotel.setId_hotel(id_hotel);
            updateHotel.setName(name);
            updateHotel.setAddress(address);
            updateHotel.setPhone(phone);
            updateHotel.setEmail(email);
            updateHotel.setDescription(description);
            updateHotel.setRating(rating);
            boolean response = hotelService.updateHotel(updateHotel);
            if (!response) {
                session.setAttribute("icon", "error");
                session.setAttribute("errorMessage", "Error al eliminar el " +
                        "hotel");
            }
            session.setAttribute("icon", "success");
            session.setAttribute("errorMessage", "Hotel eliminado " +
                    "correctamente");
            List<HotelDto> hotels = hotelService.getAllHotels();
            model.addAttribute("pageContent", "hotels");
            model.addAttribute("hotels", hotels);
            return "redirect:/hotels";
        } catch (Exception error) {
            System.out.println("ERRROR" + error.getMessage());
            model.addAttribute("hotels", List.of());
            return "layout";
        }
    }

}
