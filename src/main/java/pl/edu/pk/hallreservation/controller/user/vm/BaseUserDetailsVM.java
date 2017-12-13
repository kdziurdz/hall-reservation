package pl.edu.pk.hallreservation.controller.user.vm;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public abstract class BaseUserDetailsVM extends UserName {

    @Email
    @NotNull
    private String email;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @NotNull
    private Boolean enabled;

    @NotEmpty
    private List<String> roles;

    public BaseUserDetailsVM(String firstName, String lastName, String email, LocalDate expirationDate, Boolean enabled, List<String> roles) {
        super(firstName, lastName);
        this.email = email;
        this.expirationDate = expirationDate;
        this.enabled = enabled;
        this.roles = roles;
    }

    public BaseUserDetailsVM() {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
