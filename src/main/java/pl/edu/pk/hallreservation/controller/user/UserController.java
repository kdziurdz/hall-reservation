package pl.edu.pk.hallreservation.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.hallreservation.controller.user.mapper.UserMapper;
import pl.edu.pk.hallreservation.controller.user.vm.*;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.model.user.UserAuthority;
import pl.edu.pk.hallreservation.service.user.UserService;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        authorities.add(new UserAuthority("ROLE_USER"));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<Page<UserDetailsVM>> getAll(Pageable pageable, @RequestParam(required = false) String query) {

        Page<UserDTO> userDTOPage = userService.getPage(pageable, query);

        return new ResponseEntity<>(userDTOPage.map(userMapper::asDetailsVM), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("{id}/roles")
    public ResponseEntity<HttpStatus> updateRoles(@PathVariable Long id, @RequestBody List<String> newRoles) {

        userService.updateRoles(id, newRoles);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("{id}/remove")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable Long id) {

        userService.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("{id}/enable")
    public ResponseEntity<HttpStatus> enableUser(@PathVariable Long id) {

        userService.enable(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("{id}/disable")
    public ResponseEntity<HttpStatus> disableUser(@PathVariable Long id) {

        userService.disable(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("{id}/expirationDate")
    public ResponseEntity<HttpStatus> disableUser(@PathVariable Long id,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate newDate) {

        userService.setExpirationDate(id, newDate);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("")
    public ResponseEntity<HttpStatus> createUser(@RequestBody SaveUserVM saveUser) {

        userService.create(userMapper.asDTO(saveUser));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/search")
    public ResponseEntity<List<UserVM>> search(@RequestParam String query) {
        List<UserDTO> userDTOS = userService.search(query);

        return new ResponseEntity<>(userDTOS.stream().map(userMapper::asVM).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("actual")
    public ResponseEntity<UserDetailsVM> getActual() {
        UserDTO userDTO = userService.getActualUserDTO();
        return new ResponseEntity<>(userMapper.asDetailsVM(userDTO), HttpStatus.OK);
    }

    @PostMapping("/account-update")
    public ResponseEntity<HttpStatus> updateAccount(@RequestBody UpdateAccountVM updateVM) {

        userService.updateAccount(updateVM.getLogin(), updateVM.getFirstName(), updateVM.getLastName(), updateVM.getEmail(),
                updateVM.getOldPassword(), updateVM.getNewPassword() );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create-password")
    public ResponseEntity<HttpStatus> createPassword(@RequestBody NewPasswordCreds newPasswordCreds) {

        userService.createPassword(newPasswordCreds.getUsername(), newPasswordCreds.getOldPassword(),
                newPasswordCreds.getNewPassword());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
