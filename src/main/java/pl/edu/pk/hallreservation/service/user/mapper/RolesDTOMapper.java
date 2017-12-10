package pl.edu.pk.hallreservation.service.user.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.pk.hallreservation.model.user.User;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolesDTOMapper {
    default List<String> asDTOs(Collection<? extends GrantedAuthority> roles){
        return roles.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
