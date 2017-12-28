package pl.edu.pk.hallreservation.repository;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;
import pl.edu.pk.hallreservation.exception.ObjectNotFoundException;
import pl.edu.pk.hallreservation.model.ClassesPeriod;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ClassPeriodRepository extends BaseRepository<ClassesPeriod> {
    default ClassesPeriod getOneById(Long id){
        return findOneById(id).orElseThrow(() -> new ObjectNotFoundException("ClassesPeriod", id));
    }

//    List<ClassesPeriod> findAll();

    boolean existsByDateFromBeforeAndAndDateToAfter(LocalDate dateFrom, LocalDate dateTo);

//    Stream<ClassesPeriod> streamAll();
}