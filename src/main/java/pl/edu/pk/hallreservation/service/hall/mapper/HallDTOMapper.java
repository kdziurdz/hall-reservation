package pl.edu.pk.hallreservation.service.hall.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.hall.vm.HallVM;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LectureDTOMapper.class})
public interface HallDTOMapper {

    HallDTO asDTO(Hall hall);

    List<HallDTO> asDTOs(List<Hall> halls);
}
