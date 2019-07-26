package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.model.Category;
import jk.hotelreservationproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HotelController {


    private CategoryService categoryService;

    @Autowired
    public HotelController(CategoryService categoryService) {
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

}
