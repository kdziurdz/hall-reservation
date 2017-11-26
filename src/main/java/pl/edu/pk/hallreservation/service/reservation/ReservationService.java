package pl.edu.pk.hallreservation.service.reservation;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.model.hall.Lecture;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.repository.ReservationRepository;
import pl.edu.pk.hallreservation.service.hall.HallService;
import pl.edu.pk.hallreservation.service.UserService;
import pl.edu.pk.hallreservation.service.reservation.dto.AvailableReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;


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
                saveReservationDTO.getLessonNumbers());
        checkLessonHourReservationAvailability(saveReservationDTO.getDate(), saveReservationDTO.getLessonNumbers());

        Reservation reservation = new Reservation(new HashSet<>(saveReservationDTO.getLessonNumbers()),
                saveReservationDTO.getDate(), userService.getActualUser(), hall);

        reservationRepository.save(reservation);
    }

    public List<AvailableReservationDTO> search(LocalDate dateFrom, LocalDate dateTo, List<Integer> lessonNumbers, List<Long> hallIds) {
        List<AvailableReservationDTO> availableReservations = new ArrayList<>();
        List<Hall> halls = getListOfHalls(hallIds);

        halls.forEach(hall -> {
            for (LocalDate date = dateFrom; date.isBefore(dateTo); date = date.plusDays(1)) {
                List<Lecture> availableSlots = getAvailableSlots(hall.getLectures(), date, lessonNumbers);

                List<Integer> lessons = availableSlots.stream().map(Lecture::getLessonNumber).collect(Collectors.toList());

                availableReservations.add(new AvailableReservationDTO(date, hall.getId(),
                        hall.getName(), lessons));
            }
        });
        return availableReservations;
    }

    private List<Hall> getListOfHalls(List<Long> ids) {
        return this.hallService.get(ids);
    }

    private boolean isWeekEven(LocalDate date) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(woy) % 2 == 0;
    }

    private void checkLessonHourAvailability(Set<Lecture> lectures, LocalDate date, List<Integer> lessonNumbers) {
        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();

        Set<Lecture> desiredLectures = lectures.stream()
                .filter(lecture -> lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek
                && lecture.getEven().equals(isEven)
                && lecture.getFree()).collect(Collectors.toSet());

        for (Integer lessonNumber: lessonNumbers) {
            boolean isFree = desiredLectures.stream().anyMatch(lecture -> Objects.equals(lecture.getLessonNumber(), lessonNumber));

            if (!isFree) {
                throw new IllegalArgumentException(String
                        .format("Lesson %d, at %tD is not free. Week is even: %b", lessonNumber, date, isEven));
            }
        }
    }

    private List<Lecture> getAvailableSlots(Set<Lecture> lectures, LocalDate date, List<Integer> lessonNumbers) {


        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();

        return lectures.stream()
                .filter(Lecture::getFree)
                .filter(lecture -> lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek)
                .filter(lecture -> lecture.getEven().equals(isEven))
                .filter(lecture -> lessonNumbers.contains(lecture.getLessonNumber())).collect(Collectors.toList());

    }

    private void checkLessonHourReservationAvailability (LocalDate date, List<Integer> lessonNumbers) {

        List<Reservation> reservationsInSingleDay = reservationRepository.findAllByDate(date);

        for(Reservation reservation: reservationsInSingleDay) {
            Set<Integer> reservedLessonNumbers = reservation.getLessonNumbers();
            for(Integer desiredLessonNumber: lessonNumbers) {
                boolean lessonAlreadyReserved = reservedLessonNumbers.contains(desiredLessonNumber);

                if (lessonAlreadyReserved) {
                    throw new IllegalArgumentException(String
                            .format("Lesson %d, at %tD is already reserved", desiredLessonNumber, date));
                }
            }
        }


    }
}