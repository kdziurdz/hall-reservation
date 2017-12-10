package pl.edu.pk.hallreservation.controller.user.vm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class UserDetailsVM {

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    private Boolean enabled;

    private Long id;

    private String firstName;

    private String lastName;

    private List<String> roles;

    public UserDetailsVM(String email, LocalDate expirationDate, Boolean enabled, Long id, String firstName,
                         String lastName, List<String> roles) {
        this.email = email;
        this.expirationDate = expirationDate;
        this.enabled = enabled;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public UserDetailsVM() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
