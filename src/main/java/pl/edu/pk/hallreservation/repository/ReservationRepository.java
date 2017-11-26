package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.hall.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

    List<Reservation> findAllByDate(LocalDate date);

}