package pl.edu.pk.hallreservation.controller.hall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.model.user.UserAuthority;
import pl.edu.pk.hallreservation.repository.UserRepository;
import pl.edu.pk.hallreservation.service.HallService;
import pl.edu.pk.hallreservation.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("hall")
public class HallController {

    private final HallService hallService ;

    @Autowired
    public HallController(HallService hallService ) {
        this.hallService  = hallService ;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody HallDTO hallDTO) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<Page<HallDTO>> getAll(Pageable pageable) {

        return new ResponseEntity<>(new PageImpl<>(new ArrayList<>()), HttpStatus.OK);
    }
}
