package pl.edu.pk.hallreservation.service.classesperiod.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.model.ClassesPeriod;
import pl.edu.pk.hallreservation.service.classesperiod.dto.ClassesPeriodDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassPeriodDTOMapper {

    ClassesPeriodDTO asDTO(ClassesPeriod dto);

    List<ClassesPeriodDTO> asDTOs(List<ClassesPeriod> dtos);
}
