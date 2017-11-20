package pl.edu.pk.hallreservation.service.reservation.dto;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SaveReservationDTO {

    @NotNull
    private Long hallId;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer lessonNumber;

    public SaveReservationDTO(Long hallId, LocalDate date, Integer lessonNumber) {
        this.hallId = hallId;
        this.date = date;
        this.lessonNumber = lessonNumber;
    }

    public SaveReservationDTO() {
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
