package guru.springframework.exceptions;

public class NotFoundInInitialization extends RuntimeException {

    public NotFoundInInitialization(String message) {
        super(message);
    }
}
