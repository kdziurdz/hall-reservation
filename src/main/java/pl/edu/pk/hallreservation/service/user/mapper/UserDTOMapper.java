package pl.edu.pk.hallreservation.service.user.mapper;

import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UserDTO asDTO(User user);
}
