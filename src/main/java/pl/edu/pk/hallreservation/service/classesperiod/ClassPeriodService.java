package pl.edu.pk.hallreservation.service.classesperiod;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.ClassesPeriod;
import pl.edu.pk.hallreservation.repository.ClassPeriodRepository;
import pl.edu.pk.hallreservation.service.classesperiod.dto.ClassesPeriodDTO;
import pl.edu.pk.hallreservation.service.classesperiod.mapper.ClassPeriodDTOMapper;

import java.util.List;


@Service
public class ClassPeriodService {
    private final ClassPeriodRepository classPeriodRepository;
    private final ClassPeriodDTOMapper classPeriodDTOMapper;

    public ClassPeriodService(ClassPeriodRepository classPeriodRepository, ClassPeriodDTOMapper classPeriodDTOMapper) {
        this.classPeriodRepository = classPeriodRepository;
        this.classPeriodDTOMapper = classPeriodDTOMapper;
    }

    public void create(ClassesPeriodDTO classesPeriodDTO) {
        ClassesPeriod classesPeriod = new ClassesPeriod(classesPeriodDTO.getDateFrom(), classesPeriodDTO.getDateTo());
        classPeriodRepository.save(classesPeriod);
    }

    public void remove(Long id) {
        classPeriodRepository.delete(id);
    }

    public List<ClassesPeriodDTO> getAll() {
        return classPeriodDTOMapper.asDTOs(classPeriodRepository.findAll());
    }
}
