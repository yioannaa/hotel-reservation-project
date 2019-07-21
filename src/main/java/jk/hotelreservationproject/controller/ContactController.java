package jk.hotelreservationproject.controller;

import jk.hotelreservationproject.model.Contact;
import jk.hotelreservationproject.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String contact(Model model, Authentication auth){
        Contact contact = new Contact();
        if(auth != null){
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            contact.setEmail(userDetails.getUsername());
        }
        model.addAttribute("contact", contact);
        return "contact";
    }
}
