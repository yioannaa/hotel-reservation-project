package jk.hotelreservationproject.service;

import jk.hotelreservationproject.model.Reservation;
import jk.hotelreservationproject.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void saveReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }


}
