package pl.edu.pk.hallreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.hall.Hall;

@Repository
public interface HallRepository extends BaseRepository<Hall> {
    default Hall getOneById(Long id){
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("Hall", id));
    }
}