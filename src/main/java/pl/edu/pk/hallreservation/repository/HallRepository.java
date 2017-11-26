package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.hall.Hall;

import java.util.List;

@Repository
public interface HallRepository extends BaseRepository<Hall> {
    default Hall getOneById(Long id){
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("Hall", id));
    }

    List<Hall> findByIdIn(List<Long> ids);

    List<Hall> findByNameContainingIgnoreCase(String name);
}