package pl.edu.pk.hallreservation.model.hall;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HALLS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "NAME")})
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "HALLS_LECTURES",
            joinColumns = @JoinColumn(name = "HALL_ID"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE_ID"))
    private Set<Lecture> lectures = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "HALLS_RESERVATIONS",
            joinColumns = @JoinColumn(name = "HALL_ID"),
            inverseJoinColumns = @JoinColumn(name = "RESERVATION_ID"))
    private Set<Reservation> reservations = new HashSet<>();

    public Hall(String name, Set<Lecture> lectures, Set<Reservation> reservations) {
        this.name = name;
        this.lectures = lectures;
        this.reservations = reservations;
    }

    public Hall() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
