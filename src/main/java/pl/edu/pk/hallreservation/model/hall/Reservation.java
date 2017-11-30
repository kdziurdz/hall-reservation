package pl.edu.pk.hallreservation.model.hall;


import pl.edu.pk.hallreservation.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "LESSON_NUMBERS")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "LESSON_NUMBERS",
            joinColumns = @JoinColumn(name = "RESERVATION_ID", nullable = false, updatable = false),
            foreignKey = @ForeignKey(name = "LESSON_NUMBERS_FK1")
    )
    private Set<Integer> lessonNumbers;

    @NotNull
    @Column(name = "DATE", columnDefinition = "DATE")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "CANCLLER_ID")
    private User canceller;

    @Column(name = "CANCLLED")
    private Boolean cancelled = false;

    @Column(name = "CANCELLATION_REASON")
    private String cancellationReason;

    @ManyToOne
    @JoinTable(name = "HALLS_RESERVATIONS",
            inverseJoinColumns = @JoinColumn(name = "HALL_ID"),
            joinColumns= @JoinColumn(name = "RESERVATION_ID"))
    private Hall hall;

    public Reservation() {
    }

    public Reservation(Set<Integer> lessonNumbers, LocalDate date, User user, Hall hall) {
        this.lessonNumbers = lessonNumbers;
        this.date = date;
        this.user = user;
        this.hall = hall;
    }

    public Long getId() {
        return id;
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

    public Set<Integer> getLessonNumbers() {
        return lessonNumbers;
    }

    public void setLessonNumbers(Set<Integer> lessonNumbers) {
        this.lessonNumbers = lessonNumbers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getcanceller() {
        return canceller;
    }

    public void setcanceller(User canceller) {
        this.canceller = canceller;
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
}
