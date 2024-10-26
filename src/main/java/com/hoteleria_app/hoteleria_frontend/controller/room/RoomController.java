package com.hoteleria_app.hoteleria_frontend.controller.room;

import com.hoteleria_app.hoteleria_frontend.dto.room.RoomsDto;
import com.hoteleria_app.hoteleria_frontend.service.reservation.ReservationService;
import com.hoteleria_app.hoteleria_frontend.service.room.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final HttpServletRequest request;

    public RoomController(RoomService roomService,
                          ReservationService reservationService,
                          HttpServletRequest request
    ) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.request = request;
    }

    @GetMapping("/roomsByHotel" + "/{id_hotel}")
    public String roomsByIdHotel(@PathVariable Long id_hotel, Model model) {
        try {
            String token = (String) request.getSession().getAttribute("token");
            if (token == null) {
                return "redirect:/signIn";
            }
            model.addAttribute("pageContent", "roomsByHotel");
            List<RoomsDto> rooms = roomService.getRoomsByHotel(id_hotel);
            model.addAttribute("rooms", rooms);
            return "layout";
        } catch (Exception e) {
            model.addAttribute("pageContent", "roomsByHotel");
            return "layout";
        }
    }

    @PostMapping("/createReservation")
    public String createReservation(
            @RequestParam("initialDate") LocalDateTime initialDate,
            @RequestParam("finalDate") LocalDateTime finalDate,
            @RequestParam("idRoom") Long idRoom,
            Model model
    ) {
        try {
            System.out.println("validando los paramettros que recibe " +
                    initialDate + finalDate + idRoom);
            String response =
                    reservationService.createReservation(initialDate,
                            finalDate,
                            idRoom);
            System.out.println("RESPONSE: " + response);
            model.addAttribute("pageContent", "roomsByHotel");
            if (response.length() > 0) {
                return "redirect:/myReservations";
            }
            return "layout";
        } catch (Exception e) {
            model.addAttribute("pageContent", "roomsByHotel");
            return "layout";
        }
    }
}
