package pl.edu.pk.hallreservation.service.hall;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.repository.HallRepository;
import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.service.hall.mapper.HallDTOMapper;

import java.util.List;


@Service
public class HallService {
    private final HallRepository hallRepository;
    private final HallDTOMapper hallDTOMapper;

    public HallService(HallRepository hallRepository, HallDTOMapper hallDTOMapper) {
        this.hallRepository = hallRepository;
        this.hallDTOMapper= hallDTOMapper;
    }

    public Page<Hall> getAllPageable(Pageable pageable) {
        Page<Hall> halls = hallRepository.findAll(pageable);
        return halls;
    }

    public Hall getOne(Long id) {
        return hallRepository.getOneById(id);
    }

    public List<Hall> get(List<Long> ids) {
        return hallRepository.findByIdIn(ids);
    }

    public List<Hall> getAll() {
        return hallRepository.findAll();
    }

    public List<HallDTO> search(String query) {
        return hallDTOMapper.asDTOs(hallRepository.findByNameContainingIgnoreCase(query));
    }
}
