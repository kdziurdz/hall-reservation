package pl.edu.pk.hallreservation.service.user.dto;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private Long id;

    private String firstName;

    private String username;

    private String lastName;

    private String email;

    private LocalDate expirationDate;

    private Boolean enabled;

    private List<String> roles;

    public UserDTO(Long id, String firstName, String username, String lastName, String email,
                   LocalDate expirationDate, Boolean enabled, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.expirationDate = expirationDate;
        this.enabled = enabled;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public UserDTO() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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
}
