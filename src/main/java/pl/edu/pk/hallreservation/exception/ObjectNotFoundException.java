package pl.edu.pk.hallreservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String resourceName, Long id) {
        super(String.format("Cannot find %s with id %d", resourceName, id));
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
