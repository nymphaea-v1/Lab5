package lab5.exceptions;

public class IncorrectFieldException extends Exception {
    public IncorrectFieldException(Object details) {
        super(details.toString());
    }
}
