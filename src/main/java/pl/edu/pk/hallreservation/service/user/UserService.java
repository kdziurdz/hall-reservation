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
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.repository.UserRepository;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;
import pl.edu.pk.hallreservation.service.user.mapper.UserDTOMapper;

import javax.transaction.Transactional;

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

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
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
}
