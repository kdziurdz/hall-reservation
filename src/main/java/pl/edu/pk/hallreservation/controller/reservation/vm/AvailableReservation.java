package pl.edu.pk.hallreservation.controller.reservation.vm;

import java.time.LocalDate;
import java.util.List;

public class AvailableReservation {
   private LocalDate date;
   private Long hallId;
   private String hallName;
   private List<Integer> lessonNumbers;

    public AvailableReservation(LocalDate date, Long hallId, String hallName, List<Integer> lessonNumbers) {
        this.date = date;
        this.hallId = hallId;
        this.hallName = hallName;
        this.lessonNumbers = lessonNumbers;
    }

    public AvailableReservation() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public List<Integer> getLessonNumbers() {
        return lessonNumbers;
    }

    public void setLessonNumbers(List<Integer> lessonNumbers) {
        this.lessonNumbers = lessonNumbers;
    }
}