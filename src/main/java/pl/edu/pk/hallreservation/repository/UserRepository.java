package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.user.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findOneByUsername(String username);
}