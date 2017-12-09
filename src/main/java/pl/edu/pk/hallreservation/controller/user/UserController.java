package pl.edu.pk.hallreservation.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.user.mapper.UserMapper;
import pl.edu.pk.hallreservation.controller.user.vm.UserDetailsVM;
import pl.edu.pk.hallreservation.controller.user.vm.UserVM;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.model.user.UserAuthority;
import pl.edu.pk.hallreservation.service.user.UserService;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("sign-up")
    public ResponseEntity<User> createUser() {

        Set<UserAuthority> authorities = new HashSet<>();
        authorities.add(new UserAuthority("R_USER"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured("R_ADMIN")
    @GetMapping("")
    public ResponseEntity<Page<UserDetailsVM>> getAll(Pageable pageable, @RequestParam(required = false) String query) {

        Page<UserDTO> userDTOPage = userService.getPage(pageable, query);

        return new ResponseEntity<>(userDTOPage.map(userMapper::asDetailsVM), HttpStatus.OK);
    }
}
