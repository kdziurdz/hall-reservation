package pl.edu.pk.hallreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserNotEnabled extends RuntimeException {

    public UserNotEnabled(String message) {
        super(message);
    }
}
