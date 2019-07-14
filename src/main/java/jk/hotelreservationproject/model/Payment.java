package jk.hotelreservationproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn (name= "client_id")
    private Client client_id;
    @OneToMany
    @JoinColumn(name = "payment_reservations")
    private Reservation reservation;

}
