package pl.edu.pk.hallreservation.service.reservation;


import org.mapstruct.Mapper;
import pl.edu.pk.hallreservation.controller.reservation.vm.SaveReservationVM;
import pl.edu.pk.hallreservation.service.reservation.dto.SaveReservationDTO;


@Mapper(componentModel = "spring")
public interface ReservationMapper {

    SaveReservationDTO asDTO (SaveReservationVM vm);
}
