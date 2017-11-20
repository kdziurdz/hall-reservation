package pl.edu.pk.hallreservation.model.hall;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "DATE", columnDefinition = "DATE")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinTable(name = "HALLS_RESERVATIONS",
            inverseJoinColumns = @JoinColumn(name = "HALL_ID"),
            joinColumns= @JoinColumn(name = "RESERVATION_ID"))
    private Hall hall;


    public Reservation(Integer lessonNumber, LocalDate date, User user, Hall hall) {
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.user = user;
        this.hall = hall;
    }

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return date;
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

}
