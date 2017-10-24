package pl.edu.pk.hallreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.model.User;
import pl.edu.pk.hallreservation.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("create")
    public ResponseEntity<User> greeting(String name) {

        userRepository.save(new User("jan", "kowalski"));
        userRepository.save(new User("dupa", "jasia"));

        return new ResponseEntity<>(new User("firstname", "lastname"), HttpStatus.CREATED);
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
