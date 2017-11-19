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

    @OneToOne
    @JoinColumn(name = "HALL_ID", nullable = false)
    private Hall hall;


    public Reservation(Integer lessonNumber, LocalDate date, User user) {
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.user = user;
    }

    public Reservation() {
    }

    public Hall getHall() {
        return hall;
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

    public User getUser() {
        return user;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
