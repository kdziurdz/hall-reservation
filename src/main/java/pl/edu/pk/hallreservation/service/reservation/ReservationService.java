package pl.edu.pk.hallreservation.service.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.repository.ReservationRepository;
import pl.edu.pk.hallreservation.service.hall.HallService;
import pl.edu.pk.hallreservation.service.reservation.dto.ReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.mapper.ReservationMapper;
import pl.edu.pk.hallreservation.service.user.UserService;
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
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, HallService hallService,
                              UserService userService, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.hallService = hallService;
        this.userService = userService;
        this.reservationMapper = reservationMapper;
    }

    public void create(@NotNull SaveReservationDTO saveReservationDTO) {
        throw new ObjectNotFoundException("XXXX");
//        HallDTO hall = hallService.getOne(saveReservationDTO.getHallId());
//
//        checkLessonHourAvailability(hall.getLectures(), saveReservationDTO.getDate(),
//                saveReservationDTO.getLessonNumbers());
//        checkLessonHourReservationAvailability(saveReservationDTO.getDate(), saveReservationDTO.getLessonNumbers(), hall);
//
//        Reservation reservation = new Reservation(new HashSet<>(saveReservationDTO.getLessonNumbers()),
//                saveReservationDTO.getDate(), userService.getActualUser(), hallService.getOneEntity(hall.getId()));
//
//        reservationRepository.save(reservation);
    }

    public List<AvailableReservationDTO> search(LocalDate dateFrom, LocalDate dateTo, Integer duration, List<Long> hallIds) {
        List<AvailableReservationDTO> availableReservations = new ArrayList<>();
        List<HallDTO> halls = getListOfHalls(hallIds);

        halls.forEach(hall -> {
            for (LocalDate date = dateFrom; date.isBefore(dateTo) || date.isEqual(dateTo); date = date.plusDays(1)) {
                List<List<LectureDTO>> availableSlots = getAvailableSlots(hall.getLectures(), date, duration, hall);

                List<List<Integer>> availableLessonsSlots = availableSlots.stream()
                        .map(lectures -> lectures.stream().map(LectureDTO::getLessonNumber)
                                .collect(Collectors.toList())).collect(Collectors.toList());

                if (availableLessonsSlots.size() > 0) {
                    availableReservations.add(new AvailableReservationDTO(date, hall.getId(),
                            hall.getName(), availableLessonsSlots));
                }
            }
        });
        return availableReservations;
    }

    public Page<ReservationDTO> getPage(Pageable pageable, List<String> statuses, LocalDate dateFrom, LocalDate dateTo, List<Long> hallIds) {

        Page<Reservation> page;

        if(hallIds == null) {
            if(statuses.size() == 2) {
                page = reservationRepository.findAllByUser_idAndDateBetween(pageable,
                        userService.getActualUser().getId(), dateFrom, dateTo);
            } else if(statuses.size() == 1 && statuses.get(0).equals("CANCELLED")) {
                page = reservationRepository.findAllByUser_idAndCancelledAndDateBetween(pageable,
                        userService.getActualUser().getId(), true, dateFrom, dateTo);
            } else {
                page = reservationRepository.findAllByUser_idAndCancelledAndDateBetween(pageable,
                        userService.getActualUser().getId(), false, dateFrom, dateTo);
            }
        } else {
            return null;
        }



        return page.map(reservationMapper::asDTO);

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

    private List<List<LectureDTO>> getAvailableSlots(Set<LectureDTO> lectures, LocalDate date, Integer duration, HallDTO hall) {

        boolean isEven = isWeekEven(date);
        int dayOfWeek = date.getDayOfWeek().getValue();

        List<LectureDTO> freeHoursAtGivenDay = lectures.stream()
                .filter(LectureDTO::getFree)
                .filter(lecture -> lecture.getDayOfWeek().getNumberOfDay() == dayOfWeek)
                .filter(lecture -> lecture.getEven().equals(isEven))
                .filter(lecture -> isLessonNumberNotReserved(date, lecture.getLessonNumber(), hall))
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

    private boolean isLessonNumberNotReserved(LocalDate date, Integer lessonNumber, HallDTO hall) {

        List<Reservation> reservationsInSingleDay = reservationRepository.findAllByDateAndHall_IdAndCancelled(date, hall.getId(),
                false);
        return reservationsInSingleDay.size() == 0 || reservationsInSingleDay.stream()
                .noneMatch(reservation -> reservation.getLessonNumbers().contains(lessonNumber));
    }

    private void checkLessonHourReservationAvailability(LocalDate date, List<Integer> lessonNumbers, HallDTO hall) {

        List<Reservation> reservationsInSingleDay = reservationRepository.findAllByDateAndHall_IdAndCancelled(date, hall.getId(),
                false);

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

    public void cancel(Long reservationId, String reason) {
        Reservation reservation = reservationRepository.getOneById(reservationId);
        User user = userService.getActualUser();
        if(!reservation.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException(String.format("User with ID %d is not owner of reservation with id %d",
                    user.getId(), reservationId));
        } else {
            reservation.setCancelled(true);
            reservation.setCanceller(user);
            reservation.setCancellationReason(reason);
            reservationRepository.save(reservation);
        }
    }
}