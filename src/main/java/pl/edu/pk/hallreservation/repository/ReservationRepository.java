package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.hall.Reservation;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

}