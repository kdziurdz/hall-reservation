package pl.edu.pk.hallreservation.controller.reservation.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.edu.pk.hallreservation.controller.hall.vm.HallVM;
import pl.edu.pk.hallreservation.controller.user.vm.UserVM;

import java.time.LocalDate;
import java.util.List;

public class ReservationVM {

    private Long id;

    private List<Integer> lessonNumbers;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private UserVM user;

    private UserVM cancellerId;

    private Boolean cancelled;

    private String cancellationReason;

    private HallVM hall;

    public ReservationVM(Long id, List<Integer> lessonNumbers, LocalDate date, UserVM user,
                         UserVM cancellerId, Boolean cancelled, String cancellationReason, HallVM hall) {
        this.id = id;
        this.lessonNumbers = lessonNumbers;
        this.date = date;
        this.user = user;
        this.cancellerId = cancellerId;
        this.cancelled = cancelled;
        this.cancellationReason = cancellationReason;
        this.hall = hall;
    }

    public ReservationVM() {
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

    public UserVM getUser() {
        return user;
    }

    public void setUser(UserVM user) {
        this.user = user;
    }

    public UserVM getCancellerId() {
        return cancellerId;
    }

    public void setCancellerId(UserVM cancellerId) {
        this.cancellerId = cancellerId;
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

    public HallVM getHall() {
        return hall;
    }

    public void setHall(HallVM hall) {
        this.hall = hall;
    }
}
