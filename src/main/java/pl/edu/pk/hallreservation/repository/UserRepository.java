package pl.edu.pk.hallreservation.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.model.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findOneByFirstName(String firstName);
}