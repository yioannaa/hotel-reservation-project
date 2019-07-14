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
    @OneToOne
    @JoinColumn (name = "client_id")
    private Client client_id;
    private LocalDateTime firstDay;
    private LocalDateTime lastDay;
    private int numberOfGuests;



}
