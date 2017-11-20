package pl.edu.pk.hallreservation.service.reservation;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.model.hall.Lecture;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.repository.ReservationRepository;
import pl.edu.pk.hallreservation.service.HallService;
import pl.edu.pk.hallreservation.service.UserService;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Set;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HallService hallService;
    private final UserService userService;

    public ReservationService(ReservationRepository reservationRepository, HallService hallService,
                              UserService userService) {
        this.reservationRepository = reservationRepository;
        this.hallService = hallService;
        this.userService = userService;
    }

    public void create(@NotNull SaveReservationDTO saveReservationDTO) {
        Hall hall = hallService.getOne(saveReservationDTO.getHallId());

        checkLessonHourAvailability(hall.getLectures(), saveReservationDTO.getDate(),
                saveReservationDTO.getLessonNumber());
        checkLessonHourReservationAvailability(saveReservationDTO.getDate(), saveReservationDTO.getLessonNumber());

        Reservation reservation = new Reservation(saveReservationDTO.getLessonNumber(),
                saveReservationDTO.getDate(), userService.getActualUser(), hall);

        reservationRepository.save(reservation);
    }

    private boolean isWeekEven(LocalDate date) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(woy) % 2 == 0;
    }

    private void checkLessonHourAvailability(Set<Lecture> lectures, LocalDate date, int lessonNumber) {
        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();
        boolean isFree = lectures.stream().anyMatch(lecture -> lecture.getLessonNumber() == lessonNumber
                && lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek
                && lecture.getEven().equals(isEven)
                && lecture.getFree());

        if (!isFree) {
            throw new IllegalArgumentException(String
                    .format("Lesson %d, at %tD is not free. Week is even: %b", lessonNumber, date, isEven));
        }
    }

    private void checkLessonHourReservationAvailability (LocalDate date, int lessonNumber) {

        boolean exists = reservationRepository.existsByDateAndLessonNumber(date, lessonNumber);

        if (exists) {
            throw new IllegalArgumentException(String
                    .format("Lesson %d, at %tD is already reserved", lessonNumber, date));
        }
    }
}