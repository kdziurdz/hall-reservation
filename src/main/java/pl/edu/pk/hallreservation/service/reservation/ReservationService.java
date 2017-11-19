package pl.edu.pk.hallreservation.service.reservation;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.repository.ReservationRepository;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void create(SaveReservationDTO saveReservationDTO){

    }
}
