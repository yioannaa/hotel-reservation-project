package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.model.Request;
import jk.hotelreservationproject.model.Reservation;
import jk.hotelreservationproject.service.CategoryService;
import jk.hotelreservationproject.service.RequestService;
import jk.hotelreservationproject.service.UserService;
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
    private CategoryService categoryService;
    private UserService userService;


    @Autowired
    public HotelController(RequestService requestService, CategoryService categoryService, UserService userService) {
        this.requestService = requestService;
        this.categoryService = categoryService;
        this.userService = userService;
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
    public String checkAvailability(@ModelAttribute Request request, Authentication auth){
        requestService.saveRequest(request);
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
