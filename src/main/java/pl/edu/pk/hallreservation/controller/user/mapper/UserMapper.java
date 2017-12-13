package pl.edu.pk.hallreservation.controller.user.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.user.vm.BaseUserDetailsVM;
import pl.edu.pk.hallreservation.controller.user.vm.SaveUserVM;
import pl.edu.pk.hallreservation.controller.user.vm.UserDetailsVM;
import pl.edu.pk.hallreservation.controller.user.vm.UserVM;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserVM asVM(UserDTO userDTO);

    UserDetailsVM asDetailsVM(UserDTO userDTO);

    UserDTO asDTO(SaveUserVM saveUser);
}
