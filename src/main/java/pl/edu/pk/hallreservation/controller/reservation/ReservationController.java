package pl.edu.pk.hallreservation.controller.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.reservation.vm.AvailableReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.service.reservation.ReservationMapper;
import pl.edu.pk.hallreservation.service.reservation.ReservationService;
import pl.edu.pk.hallreservation.service.reservation.dto.AvailableReservationDTO;

import java.time.LocalDate;
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

//    @GetMapping("")
//    public ResponseEntity<SaveReservationVM> get() {
//
//        return new ResponseEntity<>(new SaveReservationVM(1L, LocalDate.now(), 1),HttpStatus.OK);
//    }

    @GetMapping("search")
    public ResponseEntity<List<AvailableReservationVM>> search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate dateTo,
                                                               @RequestParam List<Integer> lessonNumbers,
                                                               @RequestParam List<Long> hallIds) {

        List<AvailableReservationDTO> dtos =
                reservationService.search(dateFrom, dateTo, lessonNumbers, hallIds);

        return new ResponseEntity<>(reservationMapper.asVMs(dtos),HttpStatus.OK);
    }
}
