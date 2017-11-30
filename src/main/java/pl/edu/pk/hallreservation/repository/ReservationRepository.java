package pl.edu.pk.hallreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.hall.Reservation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

    List<Reservation> findAllByDateAndHall_IdAndCancelled(LocalDate date, Long hall_Id, Boolean cancelled);

    Page<Reservation> findAllByUser_idAndDateBetween(Pageable pageable, Long user_id,LocalDate dateAfter, LocalDate dateBefore);

    Page<Reservation> findAllByUser_idAndCancelledAndDateBetween(Pageable pageable, Long user_id, Boolean cancelled,
                                                               LocalDate dateAfter, LocalDate dateBefore);


}