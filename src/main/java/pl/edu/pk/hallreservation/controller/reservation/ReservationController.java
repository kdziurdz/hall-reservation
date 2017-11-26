package pl.edu.pk.hallreservation.controller.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.reservation.vm.AvailableReservation;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.service.DaysOfWeekService;
import pl.edu.pk.hallreservation.service.reservation.ReservationMapper;
import pl.edu.pk.hallreservation.service.reservation.ReservationService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService,
                                 ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> create(@RequestBody SaveReservationVM saveReservationVM) {

        reservationService.create(reservationMapper.asDTO(saveReservationVM));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<SaveReservationVM> get() {

        return new ResponseEntity<>(new SaveReservationVM(1L, LocalDate.now(), 1),HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<AvailableReservation>> search(@RequestParam @DateTimeFormat(pattern = "yyyy-dd-MM") LocalDate dateFrom,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-dd-MM")LocalDate dateTo,
                                                             @RequestParam Integer lessonFrom,
                                                             @RequestParam Integer lessonTo,
                                                             @RequestParam List<Long> hallIds) {


        reservationService.search(dateFrom, dateTo, lessonFrom, lessonTo, hallIds);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
