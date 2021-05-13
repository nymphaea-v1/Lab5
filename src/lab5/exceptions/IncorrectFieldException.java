package lab5.exceptions;

public class IncorrectFieldException extends Exception {
    public IncorrectFieldException(String details) {
        super("Incorrect field: " + details);
    }
}
