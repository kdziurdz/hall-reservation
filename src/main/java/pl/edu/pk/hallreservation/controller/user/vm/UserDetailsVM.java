package pl.edu.pk.hallreservation.controller.user.vm;

import java.time.LocalDate;
import java.util.List;

public class UserDetailsVM extends BaseUserDetailsVM {
    private Long id;

    private String username;

    public UserDetailsVM(String firstName, String lastName, String email, LocalDate expirationDate,
                         Boolean enabled, List<String> roles, Long id, String username) {
        super(firstName, lastName, email, expirationDate, enabled, roles);
        this.id = id;
    }

    public UserDetailsVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
