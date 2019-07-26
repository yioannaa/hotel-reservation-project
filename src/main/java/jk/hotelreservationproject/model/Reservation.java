package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    @NotBlank(message = "E-mail cannot be blank")
    @Email(message = "Invalid e-mail address")
    private String email;
    @Size(min = 9, message = "Invalid phone number")
    private String phone;
    private LocalDateTime registration_date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Arrival date cannot be blank")
    private LocalDateTime firstDay;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
//    @NotBlank(message = "Arrival date cannot be blank")
    private LocalDateTime lastDay;
    private int numberOfGuests;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category roomCategory;


    public Reservation(Request request, String name, String lastName, @Size(min = 9, message = "Invalid phone number") String phone, LocalDateTime registration_date) {
        this.name = name;
        this.lastName = lastName;
        this.email = request.getEmail();
        this.phone = phone;
        this.registration_date = registration_date;
        this.user = request.getUser();
        this.firstDay = request.getFirstDay();
        this.lastDay = request.getLastDay();
        this.numberOfGuests = request.getNumberOfGuests();
        this.roomCategory = request.getRoomCategory();
    }

}
