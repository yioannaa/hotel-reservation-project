package jk.hotelreservationproject.model;

import jk.hotelreservationproject.model.enums.RoomClassCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoomClass {


    @Enumerated
    private RoomClassCategory category;
    private boolean balcony;
    private boolean breakfast;
    private double area;
    @OneToOne
    @JoinColumn(name = "price_id")
    private Price price_id;

}
