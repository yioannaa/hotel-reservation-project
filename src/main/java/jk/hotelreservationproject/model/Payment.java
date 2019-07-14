package jk.hotelreservationproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "reservations")
    private List<Reservation> reservations;
    private double sum;

}
