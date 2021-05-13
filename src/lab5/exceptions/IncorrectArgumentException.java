package lab5.exceptions;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String details) {
        super("Incorrect argument (" + details + "). Try again or type -1 to cancel:");
    }
}
