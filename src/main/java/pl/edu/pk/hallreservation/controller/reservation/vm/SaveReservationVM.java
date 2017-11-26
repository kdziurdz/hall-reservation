package pl.edu.pk.hallreservation.controller.reservation.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


public class SaveReservationVM {

    @NotNull
    private Long hallId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    private List<Integer> lessonNumbers;

    public SaveReservationVM(Long hallId, LocalDate date, List<Integer> lessonNumbers) {
        this.hallId = hallId;
        this.date = date;
        this.lessonNumbers = lessonNumbers;
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

    public List<Integer> getLessonNumbers() {
        return lessonNumbers;
    }

    public void setLessonNumbers(List<Integer> lessonNumbers) {
        this.lessonNumbers = lessonNumbers;
    }
}
