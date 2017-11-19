package pl.edu.pk.hallreservation.model.hall;


import pl.edu.pk.hallreservation.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "LESSON_NUMBER")
    private Integer lessonNumber;

    @NotNull
    @Column(name = "DATE")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinTable(name = "HALLS_RESERVATIONS",
            inverseJoinColumns = @JoinColumn(name = "HALL_ID"),
            joinColumns= @JoinColumn(name = "RESERVATION_ID"))
    private Hall hall;

    public Reservation(Integer lessonNumber, LocalDate date) {
        this.lessonNumber = lessonNumber;
        this.date = date;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
