package pl.edu.pk.hallreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.repository.HallRepository;


@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Page<Hall> getAll(Pageable pageable) {
        Page<Hall> halls = hallRepository.findAll(pageable);
        return halls;
    }

    public Hall getOne(Long id) {
        return hallRepository.getOneById(id);
    }
}
