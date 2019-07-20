package jk.hotelreservationproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String phone;
    private LocalDateTime registration_date = LocalDateTime.now();
    @OneToOne
    @JoinColumn (name = "user_id")
    private User user_id;
    private LocalDateTime firstDay;
    private LocalDateTime lastDay;
    private int numberOfGuests;



}
