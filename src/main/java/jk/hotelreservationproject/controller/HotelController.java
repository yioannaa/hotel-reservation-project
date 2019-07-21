package jk.hotelreservationproject.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {


    @GetMapping("/")
    public String home(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            //przekierowuję do widoku zalogowanego użytkownika
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }

        return "/index";
    }

}
