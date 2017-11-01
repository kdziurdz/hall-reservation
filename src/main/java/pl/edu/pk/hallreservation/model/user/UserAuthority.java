package pl.edu.pk.hallreservation.model.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserAuthority implements GrantedAuthority {

    @NotNull
    @Column(name = "AUTHORITY")
    private String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }

    public UserAuthority(String authorityName) {
        this.authorityName = authorityName;
    }

    public UserAuthority() {
    }
}
