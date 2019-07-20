package jk.hotelreservationproject.model;

import jk.hotelreservationproject.model.enums.RoomClassCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomClass {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated
    private RoomClassCategory category;
    private boolean balcony;
    private boolean breakfast;
    private double area;
    private double price;
    private double childPrice;

}
