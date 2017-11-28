package pl.edu.pk.hallreservation.service.reservation;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.model.hall.Lecture;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.repository.ReservationRepository;
import pl.edu.pk.hallreservation.service.hall.HallService;
import pl.edu.pk.hallreservation.service.UserService;
import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.service.hall.dto.LectureDTO;
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
        HallDTO hall = hallService.getOne(saveReservationDTO.getHallId());

        checkLessonHourAvailability(hall.getLectures(), saveReservationDTO.getDate(),
                saveReservationDTO.getLessonNumbers());
        checkLessonHourReservationAvailability(saveReservationDTO.getDate(), saveReservationDTO.getLessonNumbers());

        Reservation reservation = new Reservation(new HashSet<>(saveReservationDTO.getLessonNumbers()),
                saveReservationDTO.getDate(), userService.getActualUser(), hallService.getOneEntity(hall.getId()));

        reservationRepository.save(reservation);
    }

    public List<AvailableReservationDTO> search(LocalDate dateFrom, LocalDate dateTo, Integer duration, List<Long> hallIds) {
        List<AvailableReservationDTO> availableReservations = new ArrayList<>();
        List<HallDTO> halls = getListOfHalls(hallIds);

        halls.forEach(hall -> {
            for (LocalDate date = dateFrom; date.isBefore(dateTo) || date.isEqual(dateTo); date = date.plusDays(1)) {
                List<List<LectureDTO>> availableSlots = getAvailableSlots(hall.getLectures(), date, duration);

                List<List<Integer>> availableLessonsSlots = availableSlots.stream()
                        .map(lectures -> lectures.stream().map(LectureDTO::getLessonNumber).collect(Collectors.toList())).collect(Collectors.toList());

                if (availableLessonsSlots.size() > 0) {
                    availableReservations.add(new AvailableReservationDTO(date, hall.getId(),
                            hall.getName(), availableLessonsSlots));
                }
            }
        });
        return availableReservations;
    }

    private List<HallDTO> getListOfHalls(List<Long> ids) {
        return ids != null ? this.hallService.get(ids) : this.hallService.getAll();
    }

    private boolean isWeekEven(LocalDate date) {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return date.get(woy) % 2 == 0;
    }

    private void checkLessonHourAvailability(Set<LectureDTO> lectures, LocalDate date, List<Integer> lessonNumbers) {
        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();

        Set<LectureDTO> desiredLectures = lectures.stream()
                .filter(lecture -> lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek
                        && lecture.getEven().equals(isEven)
                        && lecture.getFree()).collect(Collectors.toSet());

        for (Integer lessonNumber : lessonNumbers) {
            boolean isFree = desiredLectures.stream().anyMatch(lecture -> Objects.equals(lecture.getLessonNumber(), lessonNumber));

            if (!isFree) {
                throw new IllegalArgumentException(String
                        .format("Lesson %d, at %tD is not free. Week is even: %b", lessonNumber, date, isEven));
            }
        }
    }

    private List<List<LectureDTO>> getAvailableSlots(Set<LectureDTO> lectures, LocalDate date, Integer duration) {


        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();

        List<LectureDTO> freeHoursAtGivenDay = lectures.stream()
                .filter(LectureDTO::getFree)
                .filter(lecture -> lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek)
                .filter(lecture -> lecture.getEven().equals(isEven))
                .sorted(Comparator.comparing(LectureDTO::getLessonNumber))
                .collect(Collectors.toList());

        List<List<LectureDTO>> resultListSlot = new ArrayList<>();

        duration = duration - 1;

        if (freeHoursAtGivenDay.size() >= duration) {
            for (int i = 0; i + duration < freeHoursAtGivenDay.size(); i++) {
                int lessonStartNumber = freeHoursAtGivenDay.get(i).getLessonNumber();
                if (freeHoursAtGivenDay.get(i + duration)
                        .getLessonNumber().equals(lessonStartNumber + duration)) {
                    resultListSlot.add(new ArrayList<>(freeHoursAtGivenDay.subList(i, i + duration + 1)));
                }
            }
            return resultListSlot;
        } else {
            return new ArrayList<>();
        }
    }

    private void checkLessonHourReservationAvailability(LocalDate date, List<Integer> lessonNumbers) {

        List<Reservation> reservationsInSingleDay = reservationRepository.findAllByDate(date);

        for (Reservation reservation : reservationsInSingleDay) {
            Set<Integer> reservedLessonNumbers = reservation.getLessonNumbers();
            for (Integer desiredLessonNumber : lessonNumbers) {
                boolean lessonAlreadyReserved = reservedLessonNumbers.contains(desiredLessonNumber);

                if (lessonAlreadyReserved) {
                    throw new IllegalArgumentException(String
                            .format("Lesson %d, at %tD is already reserved", desiredLessonNumber, date));
                }
            }
        }


    }
}