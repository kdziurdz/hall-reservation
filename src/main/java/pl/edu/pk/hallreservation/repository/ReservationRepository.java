package pl.edu.pk.hallreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.hall.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

    List<Reservation> findAllByDateAndHall_IdAndCancelled(LocalDate date, Long hall_Id, Boolean cancelled);

    Page<Reservation> findAllByUser_idInAndDateBetween(Pageable pageable, List<Long> userIds, LocalDate dateAfter, LocalDate dateBefore);

    Page<Reservation> findAllByUser_idInAndDateBetweenAndHall_IdIn(Pageable pageable, List<Long> userIds, LocalDate dateAfter, LocalDate dateBefore, List<Long> hallIds);

    Page<Reservation> findAllByUser_idInAndCancelledAndDateBetween(Pageable pageable, List<Long> userIds, Boolean cancelled,
                                                                 LocalDate dateAfter, LocalDate dateBefore);

    default Reservation getOneById(Long id) {
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("Reservation", id));
    }

    Page<Reservation> findAllByUser_idInAndCancelledAndDateBetweenAndHall_IdIn(Pageable pageable, List<Long> ids, boolean b,
                                                                             LocalDate dateFrom, LocalDate dateTo,
                                                                             List<Long> hallIds);

    Page<Reservation> findAllByAndDateBetween(Pageable pageable, LocalDate dateFrom, LocalDate dateTo);

    Page<Reservation> findAllByAndCancelledAndDateBetween(Pageable pageable, boolean b, LocalDate dateFrom, LocalDate dateTo);

    Page<Reservation> findAllByHall_idInAndCancelledAndDateBetween(Pageable pageable, List<Long> hallIds, boolean cancelled, LocalDate dateFrom, LocalDate dateTo);

    Page<Reservation> findAllByHall_idInAndDateBetween(Pageable pageable, List<Long> hallIds, LocalDate dateFrom, LocalDate dateTo);
}