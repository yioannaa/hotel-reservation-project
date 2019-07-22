package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.model.Request;
import jk.hotelreservationproject.model.Reservation;
import jk.hotelreservationproject.service.CategoryService;
import jk.hotelreservationproject.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HotelController {

    private RequestService requestService;
    private CategoryService categoryService;

    @Autowired
    public HotelController(RequestService requestService, CategoryService categoryService) {
        this.requestService = requestService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        List<Category> categories = categoryService.showAllCategories();
        model.addAttribute("categories", categories);
        System.out.println(categories);
        return "/index";
    }

    @GetMapping("/index")
    public String index(Model model, Authentication auth){
        return home(model, auth);
    }

    @GetMapping("/addrequest")
    public String checkAvailability(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        model.addAttribute("request", new Reservation());
        return "addrequest";
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
