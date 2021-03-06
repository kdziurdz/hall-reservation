package pl.edu.pk.hallreservation.controller.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.reservation.vm.AvailableReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.ReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.service.reservation.ReservationService;
import pl.edu.pk.hallreservation.service.reservation.dto.AvailableReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.dto.ReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.mapper.ReservationMapper;

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

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("search")
    public ResponseEntity<List<AvailableReservationVM>> search(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                                               @RequestParam Integer duration,
                                                               @RequestParam(required = false) List<Long> hallIds) {

        List<AvailableReservationDTO> dtos =
                reservationService.search(dateFrom, dateTo, duration, hallIds);

        return new ResponseEntity<>(reservationMapper.asAvailableReservationsVM(dtos), HttpStatus.OK);
    }

    @PatchMapping("{id}/cancel")
    public ResponseEntity<HttpStatus> cancel(@PathVariable Long id, @RequestParam(required = false) String reason) {

        reservationService.cancel(id, reason);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<Page<ReservationVM>> search(Pageable pageable, @RequestParam List<String> status,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                                      @RequestParam(required = false) List<Long> hallIds) {

        Page<ReservationDTO> dtos = reservationService.getPage(pageable, status, dateFrom, dateTo, hallIds);

        return new ResponseEntity<>(dtos.map(reservationMapper::asVM), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("by-users")
    public ResponseEntity<Page<ReservationVM>> searchByUsers(Pageable pageable, @RequestParam List<String> status,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                                             @RequestParam(required = false) List<Long> userIds,
                                                             @RequestParam(required = false) List<Long> hallIds) {

        Page<ReservationDTO> dtos = reservationService.getPage(pageable, status, dateFrom, dateTo, hallIds, userIds);

        return new ResponseEntity<>(dtos.map(reservationMapper::asVM), HttpStatus.OK);
    }
}
