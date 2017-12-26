package pl.edu.pk.hallreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findOneByUsername(String username);

    default User getOneById(Long id){
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("User", id));
    }

    Page<User> findAllByFirstNameContainingOrLastNameContaining(Pageable pageable, String firstName, String lastName);

    default User getOneByUsername(String username) {
        User user = findOneByUsername(username);
        if(user == null) {
            throw new ObjectNotFoundException("No user with username" + username);
        }
        return user;
    }

    List<User> findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String query, String query1);
}