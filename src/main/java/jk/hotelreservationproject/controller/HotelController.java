package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.model.Request;
import jk.hotelreservationproject.model.Reservation;
import jk.hotelreservationproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HotelController {

    private RequestService requestService;
    private ReservationService reservationService;
    private CategoryService categoryService;
    private UserService userService;
    private AutoMailingService autoMailingService;

    @Autowired
    public HotelController(RequestService requestService, ReservationService reservationService, CategoryService categoryService, UserService userService, AutoMailingService autoMailingService) {
        this.requestService = requestService;
        this.reservationService = reservationService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.autoMailingService = autoMailingService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        Request request = new Request();
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
            request.setUser(userService.getUserByEmail(userDetails.getUsername()));
            System.out.println(userService.getUserByEmail(userDetails.getUsername()));
        }
        List<Category> categories = categoryService.showAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("request", request);
        System.out.println(categories);
        return "/index";
    }

    @GetMapping("/index")
    public String index(Model model, Authentication auth){
        return home(model, auth);
    }

    @GetMapping("/addrequest")
    public String checkAvailability(Model model, Authentication auth){
        Reservation reservation = new Reservation();
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        model.addAttribute("reservation", reservation);
        return "/request";
    }

    @PostMapping("/addrequest")
    public String checkAvailability(@ModelAttribute Reservation reservation, Authentication auth){
        reservationService.saveReservation(reservation);
        autoMailingService.sendMessage(reservation.getEmail(),
                "Hotel Message Confirmation",
                "Thank you for your message :)");
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "/about";
    }

    @GetMapping("/restaurant")
    public String restaurant(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "/restaurant";
    }

    @GetMapping("/rooms")
    public String showRooms(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "/rooms";
    }

}
