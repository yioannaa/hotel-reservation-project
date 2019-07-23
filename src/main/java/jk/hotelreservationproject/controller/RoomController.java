package jk.hotelreservationproject.controller;


import jk.hotelreservationproject.service.RoomService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class RoomController {

    RoomService roomService;

    @GetMapping("/rooms")
    public String showRooms(Model model, Authentication auth){
        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "rooms";
    }
//    @GetMapping("/rooms/{roomCategory_id}")
//    public String showRoomByCategory(@PathVariable Long category_id, Model model) {
//
//        model.addAttribute("category_id", roomService.getRoomByCategoryId(category_id) );
//        return "";
//    }

    @GetMapping("/classic-room")
    public String showClassicRoom(Model model, Authentication auth) {

        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "classic-room";
    }

    @GetMapping("/family-room")
    public String showFamilyRoom(Model model, Authentication auth) {

        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "family-room";
    }

    @GetMapping("/deluxe-room")
    public String showDeluxeRoom(Model model, Authentication auth) {

        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "deluxe-room";
    }

    @GetMapping("/dormitory-room")
    public String showDormitoryRoom(Model model, Authentication auth) {

        if (auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            model.addAttribute("loggedEmail", userDetails.getUsername());
        }
        return "dormitory-room";
    }






}
