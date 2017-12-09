package pl.edu.pk.hallreservation.controller.user.vm;

import java.time.LocalDate;

public class UserDetailsVM {

    private String email;

    private LocalDate expirationDate;

    private Boolean enabled;

    private Long id;

    private String firstName;

    private String lastName;

    public UserDetailsVM() {
    }

    public UserDetailsVM(String email, LocalDate expirationDate, Boolean enabled,
                         Long id, String firstName, String lastName) {
        this.email = email;
        this.expirationDate = expirationDate;
        this.enabled = enabled;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
