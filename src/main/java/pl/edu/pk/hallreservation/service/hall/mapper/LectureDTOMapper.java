package pl.edu.pk.hallreservation.service.hall.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.model.hall.Lecture;
import pl.edu.pk.hallreservation.service.hall.dto.LectureDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LectureDTOMapper {

    LectureDTO asDTO(Lecture lecture);

    List<LectureDTO> asDTOs(List<Lecture> lectures);
}
