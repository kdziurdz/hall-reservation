package pl.edu.pk.hallreservation.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.exception.ConflictDataException;
import pl.edu.pk.hallreservation.exception.InvalidDateException;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.model.user.UserAuthority;
import pl.edu.pk.hallreservation.repository.UserRepository;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;
import pl.edu.pk.hallreservation.service.user.mapper.UserDTOMapper;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username);
    }

    public User getActualUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userRepository.findOneByUsername(authentication.getName());
        } else {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }
    }

    public Page<UserDTO> getPage(Pageable pageable, String query) {
        Page<User> users;
        if(query != null) {
            users = userRepository.findAllByFirstNameContainingOrLastNameContaining(pageable, query, query);
        } else {
            users = userRepository.findAll(pageable);
        }

        return users.map(userDTOMapper::asDTO);
    }

    public void updateRoles(Long userId, List<String> newRoles) {
        User user = userRepository.getOneById(userId);
        user.setAuthorities(newRoles.stream()
                .map(UserAuthority::new).collect(Collectors.toSet()));
        userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.delete(id);
    }

    public void enable(Long id) {
        User user = userRepository.getOneById(id);
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void disable(Long id) {
        User user = userRepository.getOneById(id);
        user.setEnabled(false);
        userRepository.save(user);
    }

    public void setExpirationDate(Long id, LocalDate newDate) {
        if(newDate.isBefore(LocalDate.now())) {
            throw new InvalidDateException("Invalid date");
        }
        User user = userRepository.getOneById(id);
        user.setExpirationDate(newDate);
        userRepository.save(user);
    }

    public void create(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getUsername(), userDTO.getLastName(), userDTO.getPassword(),
                userDTO.getEmail(), userDTO.getExpirationDate(), userDTO.getEnabled(), userDTO.getRoles().stream()
                .map(UserAuthority::new).collect(Collectors.toSet()), true);

        userRepository.save(user);
    }

    public void createPassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.getOneByUsername(username);
        if(!user.getPassword().equals(oldPassword)) {
            throw new ObjectNotFoundException("User Credentials does not match");
        }

        if(user.isAccountNonLocked()) {
            throw new ConflictDataException("User is not locked");
        }

        user.setPassword(newPassword);
        user.setFirstLogin(false);
        userRepository.save(user);
    }

    public List<UserDTO> search(String query) {
        List<User> foundUsers = userRepository.findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(query, query);
        return foundUsers.stream().map(userDTOMapper::asDTO).collect(Collectors.toList());
    }
}