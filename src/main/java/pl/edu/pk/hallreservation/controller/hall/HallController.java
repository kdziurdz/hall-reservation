package pl.edu.pk.hallreservation.controller.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.repository.HallRepository;
import pl.edu.pk.hallreservation.service.HallParserService;
import pl.edu.pk.hallreservation.service.HallService;

import java.util.ArrayList;

@RestController
@RequestMapping("hall")
public class HallController {

    private final HallParserService hallParserService;
    private final HallService hallService;

    @Autowired
    public HallController(HallParserService hallParserService, HallRepository hallRepository,
                          HallService hallService) {
        this.hallParserService = hallParserService;
        this.hallService = hallService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Hall>> getAll(Pageable pageable) {
        return new ResponseEntity<>(hallService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("refresh")
    public ResponseEntity<HttpStatus> refreshHalls() {
        hallParserService.refreshHallsClasses();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getAll(@PathVariable Long id) {
        return new ResponseEntity<>(hallService.getOne(id), HttpStatus.OK);
    }
}
