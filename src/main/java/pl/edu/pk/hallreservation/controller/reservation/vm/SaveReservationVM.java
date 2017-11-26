package pl.edu.pk.hallreservation.controller.reservation.vm;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class SaveReservationVM {

    @NotNull
    private Long hallId;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer lessonNumber; // TODO array

    public SaveReservationVM(Long hallId, LocalDate date, Integer lessonNumber) {
        this.hallId = hallId;
        this.date = date;
        this.lessonNumber = lessonNumber;
    }

    public SaveReservationVM() {
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
