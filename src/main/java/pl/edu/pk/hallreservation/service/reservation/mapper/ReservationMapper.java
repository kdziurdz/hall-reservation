package pl.edu.pk.hallreservation.service.reservation.mapper;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.reservation.vm.AvailableReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.ReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.controller.user.mapper.UserMapper;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.service.reservation.dto.AvailableReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.dto.ReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;
import pl.edu.pk.hallreservation.service.user.mapper.UserDTOMapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReservationMapper {

    SaveReservationDTO asDTO(SaveReservationVM vm);

    ReservationDTO asDTO(Reservation vm);

    AvailableReservationVM asVM(AvailableReservationDTO dto);

    List<AvailableReservationVM> asAvailableReservationsVM(List<AvailableReservationDTO> dto);

    ReservationVM asVM(ReservationDTO dto);

    List<ReservationVM> asReservationVMs(List<ReservationDTO> dto);
}
