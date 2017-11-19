package pl.edu.pk.hallreservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long> {

    @Override List<T> findAll();

    Optional<T> findOneById(Long id);

    Page<T> findAll(Pageable pageable);
}