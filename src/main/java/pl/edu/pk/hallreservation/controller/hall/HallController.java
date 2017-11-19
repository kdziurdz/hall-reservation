package pl.edu.pk.hallreservation.controller.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.service.HallParserService;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("hall")
public class HallController {

    private final HallParserService hallParserService;

    @Autowired
    public HallController(HallParserService hallParserService) {
        this.hallParserService = hallParserService;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody HallDTO hallDTO) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<HallDTO>> getAll(Pageable pageable) {
        hallParserService.refreshHallsClasses();
        return new ResponseEntity<>(new PageImpl<>(new ArrayList<>()), HttpStatus.OK);
    }
}
