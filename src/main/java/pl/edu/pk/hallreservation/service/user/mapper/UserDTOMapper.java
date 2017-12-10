package pl.edu.pk.hallreservation.service.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {RolesDTOMapper.class})
public interface UserDTOMapper {

    @Mapping(target = "roles", source = "authorities")
    UserDTO asDTO(User user);
}
