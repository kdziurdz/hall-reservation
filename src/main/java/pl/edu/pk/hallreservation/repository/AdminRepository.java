package pl.edu.pk.hallreservation.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.ClassesPeriod;

import java.util.List;

@Repository
public interface AdminRepository extends BaseRepository<ClassesPeriod> {
    default ClassesPeriod getOneById(Long id){
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("ClassesPeriod", id));
    }

    List<ClassesPeriod > findAll();
}