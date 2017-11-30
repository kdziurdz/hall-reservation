package pl.edu.pk.hallreservation.controller.user.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.user.vm.UserVM;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVM asVM(UserDTO userDTO);
}
