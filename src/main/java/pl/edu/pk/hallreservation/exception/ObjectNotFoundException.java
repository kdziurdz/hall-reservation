package pl.edu.pk.hallreservation.exception;


public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String resourceName, Long id) {
        super(String.format("Cannot find %s with id %d", resourceName, id));
    }
}
