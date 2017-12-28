package pl.edu.pk.hallreservation.service.admin.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.model.ClassesPeriod;
import pl.edu.pk.hallreservation.service.admin.dto.ClassesPeriodDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminDTOMapper {

    ClassesPeriodDTO asDTO(ClassesPeriod dto);

    List<ClassesPeriodDTO> asDTOs(List<ClassesPeriod> dtos);
}
