package pl.edu.pk.hallreservation.model.hall;


import pl.edu.pk.hallreservation.model.DayOfWeek;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "LECTURES",
        uniqueConstraints = {@UniqueConstraint(columnNames = "LESSON_NUMBER"),
                @UniqueConstraint(columnNames = "IS_EVEN"),
                @UniqueConstraint(columnNames = "DAY_OF_WEEK")})
public class Lecture {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "LESSON_NUMBER")
    private Integer lessonNumber;

    @NotNull
    @Column(name = "IS_EVEN")
    private Boolean isEven;

    @NotNull
    @Column(name = "IS_FREE")
    private Boolean isFree;

    @NotNull
    @Column(name = "DAY_OF_WEEK")
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinTable(name = "HALLS_LECTURES",
            inverseJoinColumns = @JoinColumn(name = "HALL_ID"),
            joinColumns= @JoinColumn(name = "LECTURE_ID"))
    private Hall hall;

    public Lecture(Integer lessonNumber, Boolean isEven, Boolean isFree, DayOfWeek dayOfWeek) {
        this.lessonNumber = lessonNumber;
        this.isEven = isEven;
        this.isFree = isFree;
        this.dayOfWeek = dayOfWeek;
    }

    public Lecture() {
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }


    public Boolean getEven() {
        return isEven;
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


    public Long getId() {
        return id;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public void setEven(Boolean even) {
        isEven = even;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
