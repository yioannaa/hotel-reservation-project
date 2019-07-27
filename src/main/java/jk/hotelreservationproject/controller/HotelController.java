package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.model.Request;
import jk.hotelreservationproject.model.Reservation;
import jk.hotelreservationproject.service.*;
import jk.hotelreservationproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String home(Request request, Model model, Authentication auth){
//        Request request = new Request();
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        List<Category> categories = categoryService.showAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("request", request);

        return "/index";
    }

    @GetMapping("/index")
    public String index(@ModelAttribute Request request, Model model, Authentication auth){
        return home(request, model, auth);
    }

    @GetMapping("/request")
    public String checkAvailability(Model model, Authentication auth){
        Request request = new Request();
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        model.addAttribute("request", request);
        return "/request";
    }

    @PostMapping("/")
    public String checkAvailability(@ModelAttribute Request request,
                                    Authentication auth,
                                    Model model,
                                    BindingResult bindingResult,
                                    RedirectAttributes ra){
        boolean dateValidation = request.isDateValid();
        if (bindingResult.hasErrors() ||!(dateValidation)){
            ra.addFlashAttribute("dateValidation", dateValidation);
            return "redirect:/";
        }
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
            request.setUser(userService.getUserByEmail(auth.getName()));
            request.setEmail(auth.getName());
        }
        requestService.saveRequest(request);
        Reservation reservation = new Reservation();
        reservation.setEmail(request.getEmail());
        reservation.setFirstDay(request.getFirstDay());
        reservation.setLastDay(request.getLastDay());

        System.out.println(reservation.getFirstDay());
        reservation.setNumberOfGuests(request.getNumberOfGuests());
        reservation.setRoomCategory(request.getRoomCategory());
        model.addAttribute("reservation", reservation);
//        reservationService.saveReservation(reservation);
//        autoMailingService.sendMessage(reservation.getEmail(),
//                "Hotel Message Confirmation",
//                "Thank you for your reservation :)");
        return "/request";
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
