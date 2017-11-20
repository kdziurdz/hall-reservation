package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.hall.Reservation;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

    boolean existsByDateAndLessonNumber(LocalDate date, Integer lessonNumber);

}