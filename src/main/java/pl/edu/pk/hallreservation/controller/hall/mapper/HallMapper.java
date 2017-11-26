package pl.edu.pk.hallreservation.controller.hall.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.hall.vm.HallVM;
import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HallMapper {

    HallVM asVM(HallDTO dto);

    List<HallVM> asVMs(List<HallDTO> dto);
}
