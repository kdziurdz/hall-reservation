package pl.edu.pk.hallreservation.controller.classesperiod.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.classesperiod.vm.ClassesPeriodVM;
import pl.edu.pk.hallreservation.controller.classesperiod.vm.SaveClassesPeriodVM;
import pl.edu.pk.hallreservation.service.classesperiod.dto.ClassesPeriodDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassesPeriodMapper {

    ClassesPeriodVM asVM(ClassesPeriodDTO dto);

    ClassesPeriodDTO asDTO(ClassesPeriodVM vm);

    ClassesPeriodDTO asDTO(SaveClassesPeriodVM vm);

    List<ClassesPeriodVM> asVMs(List<ClassesPeriodDTO> dto);

    List<ClassesPeriodDTO> asDTOs(List<ClassesPeriodVM> dto);
}
