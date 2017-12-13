package pl.edu.pk.hallreservation.controller.user.vm;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class SaveUserVM extends BaseUserDetailsVM {
    @NotNull
    private String password;

    @NotNull
    private String username;

    public SaveUserVM(String firstName, String lastName, String email, LocalDate expirationDate, Boolean enabled,
                      List<String> roles, String password, String username) {
        super(firstName, lastName, email, expirationDate, enabled, roles);
        this.password = password;
        this.username = username;
    }

    public SaveUserVM() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

