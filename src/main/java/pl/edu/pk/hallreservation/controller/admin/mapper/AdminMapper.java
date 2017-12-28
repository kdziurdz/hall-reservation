package pl.edu.pk.hallreservation.controller.admin.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.admin.vm.ClassesPeriodVM;
import pl.edu.pk.hallreservation.controller.admin.vm.SaveClassesPeriodVM;
import pl.edu.pk.hallreservation.service.admin.dto.ClassesPeriodDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    ClassesPeriodVM asVM(ClassesPeriodDTO dto);

    ClassesPeriodDTO asDTO(ClassesPeriodVM vm);

    ClassesPeriodDTO asDTO(SaveClassesPeriodVM vm);

    List<ClassesPeriodVM> asVMs(List<ClassesPeriodDTO> dto);

    List<ClassesPeriodDTO> asDTOs(List<ClassesPeriodVM> dto);
}
