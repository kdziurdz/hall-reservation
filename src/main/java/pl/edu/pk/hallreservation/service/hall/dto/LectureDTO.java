package pl.edu.pk.hallreservation.service.hall.dto;

import pl.edu.pk.hallreservation.model.DayOfWeek;

public class LectureDTO {
    private Long id;

    private Integer lessonNumber;

    private Boolean isEven;

    private Boolean isFree;

    private DayOfWeek dayOfWeek;

    public LectureDTO(Long id, Integer lessonNumber, Boolean isEven, Boolean isFree, DayOfWeek dayOfWeek) {
        this.id = id;
        this.lessonNumber = lessonNumber;
        this.isEven = isEven;
        this.isFree = isFree;
        this.dayOfWeek = dayOfWeek;
    }

    public LectureDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public Boolean getEven() {
        return isEven;
    }

    public void setEven(Boolean even) {
        isEven = even;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
