package pl.edu.pk.hallreservation.controller.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.hall.vm.HallVM;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.service.HallParserService;
import pl.edu.pk.hallreservation.controller.hall.mapper.HallMapper;
import pl.edu.pk.hallreservation.service.hall.HallService;
import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;

import java.util.List;

@RestController
@RequestMapping("hall")
public class HallController {

    private final HallParserService hallParserService;
    private final HallService hallService;
    private final HallMapper hallMapper;

    @Autowired
    public HallController(HallParserService hallParserService,
                          HallService hallService, HallMapper hallMapper) {
        this.hallParserService = hallParserService;
        this.hallService = hallService;
        this.hallMapper = hallMapper;
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

    @GetMapping("/search")
    public ResponseEntity<List<HallVM>> search(@RequestParam String query) {
        List<HallDTO> hallDTOs = hallService.search(query);
        return new ResponseEntity<>(hallMapper.asVMs(hallDTOs), HttpStatus.OK);
    }
}
