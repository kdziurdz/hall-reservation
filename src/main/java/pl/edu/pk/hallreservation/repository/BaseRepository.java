package pl.edu.pk.hallreservation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pk.hallreservation.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
}