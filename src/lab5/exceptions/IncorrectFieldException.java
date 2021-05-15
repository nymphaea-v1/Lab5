package lab5.exceptions;

public class IncorrectFieldException extends Exception {
    public IncorrectFieldException(String details) {
        super(details);
    }

    public IncorrectFieldException() {
        super("incorrect field");
    }
}
