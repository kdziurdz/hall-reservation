package pl.edu.pk.hallreservation.service.reservation.dto;

import pl.edu.pk.hallreservation.service.hall.dto.HallDTO;
import pl.edu.pk.hallreservation.service.user.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
    private Long id;

    private List<Integer> lessonNumbers;

    private LocalDate date;

    private UserDTO user;

    private UserDTO canceller;

    private Boolean cancelled;

    private String cancellationReason;

    private HallDTO hall;

    public ReservationDTO(Long id, List<Integer> lessonNumbers, LocalDate date, UserDTO user,
                          UserDTO canceller, Boolean cancelled, String cancellationReason, HallDTO hall) {
        this.id = id;
        this.lessonNumbers = lessonNumbers;
        this.date = date;
        this.user = user;
        this.canceller = canceller;
        this.cancelled = cancelled;
        this.cancellationReason = cancellationReason;
        this.hall = hall;
    }

    public ReservationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getLessonNumbers() {
        return lessonNumbers;
    }

    public void setLessonNumbers(List<Integer> lessonNumbers) {
        this.lessonNumbers = lessonNumbers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getcanceller() {
        return canceller;
    }

    public void setcanceller(UserDTO canceller) {
        this.canceller = canceller;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public HallDTO getHall() {
        return hall;
    }

    public void setHall(HallDTO hall) {
        this.hall = hall;
    }
}
