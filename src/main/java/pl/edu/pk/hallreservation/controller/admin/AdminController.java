package pl.edu.pk.hallreservation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pk.hallreservation.controller.admin.mapper.AdminMapper;
import pl.edu.pk.hallreservation.controller.admin.vm.SaveClassesPeriodVM;
import pl.edu.pk.hallreservation.controller.hall.vm.HallVM;
import pl.edu.pk.hallreservation.service.admin.AdminService;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminController(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }


    @PostMapping("classes-peroid")
    public ResponseEntity<HttpStatus> search(@RequestBody SaveClassesPeriodVM saveClassesPeriod) {

        adminService.create(adminMapper.asDTO(saveClassesPeriod));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
