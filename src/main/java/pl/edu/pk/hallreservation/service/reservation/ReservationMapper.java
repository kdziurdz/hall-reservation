package pl.edu.pk.hallreservation.service.reservation;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.reservation.vm.AvailableReservationVM;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.service.reservation.dto.AvailableReservationDTO;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

    SaveReservationDTO asDTO(SaveReservationVM vm);

    AvailableReservationVM asVM(AvailableReservationDTO dto);

    List<AvailableReservationVM> asVMs(List<AvailableReservationDTO> dto);
}
