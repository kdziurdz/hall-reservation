package pl.edu.pk.hallreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.model.User;
import pl.edu.pk.hallreservation.repository.UserRepository;
import pl.edu.pk.hallreservation.service.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                          UserService userService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<HttpStatus> signUp() {

        userService.createUser(new User("jan", "kowalski", bCryptPasswordEncoder
                .encode("password"), "ema@il"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("count")
    public Long count() {

        Iterable<User> users = userRepository.findAll();

        System.out.println(users.hashCode());

        return userRepository.count();
    }

    @GetMapping("")
    public List<User> getAll() {

        return userRepository.findAll();
    }
}
