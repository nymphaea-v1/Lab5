package lab5.exceptions;

public class UnreadableInputException extends Exception {
    public UnreadableInputException(String details) {
        super("Incorrect input (" + details + "). Try again or type -1 to cancel:");
    }
}
