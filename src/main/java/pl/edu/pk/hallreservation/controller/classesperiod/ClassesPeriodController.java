package pl.edu.pk.hallreservation.controller.classesperiod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.classesperiod.mapper.ClassesPeriodMapper;
import pl.edu.pk.hallreservation.controller.classesperiod.vm.ClassesPeriodVM;
import pl.edu.pk.hallreservation.controller.classesperiod.vm.SaveClassesPeriodVM;
import pl.edu.pk.hallreservation.service.classesperiod.ClassPeriodService;
import pl.edu.pk.hallreservation.service.classesperiod.dto.ClassesPeriodDTO;

import java.util.List;

@RestController
//@Secured("ROLE_ADMIN")
@RequestMapping("classes-period")
public class ClassesPeriodController {

    private final ClassPeriodService classPeriodService;
    private final ClassesPeriodMapper classesPeriodMapper;

    @Autowired
    public ClassesPeriodController(ClassPeriodService classPeriodService, ClassesPeriodMapper classesPeriodMapper) {
        this.classPeriodService = classPeriodService;
        this.classesPeriodMapper = classesPeriodMapper;
    }


    @PostMapping("")
    public ResponseEntity<HttpStatus> search(@RequestBody SaveClassesPeriodVM saveClassesPeriod) {

        classPeriodService.create(classesPeriodMapper.asDTO(saveClassesPeriod));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> search(@PathVariable Long id){

        classPeriodService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<ClassesPeriodVM>> getAll(){

        List<ClassesPeriodDTO> dtos = classPeriodService.getAll();

        return new ResponseEntity<>(classesPeriodMapper.asVMs(dtos),HttpStatus.OK);
    }
}
