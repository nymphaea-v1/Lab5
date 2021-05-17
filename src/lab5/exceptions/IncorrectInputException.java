package lab5.exceptions;

public class IncorrectInputException extends Exception {
    public IncorrectInputException(String details) {
        super("Incorrect input (" + details + "). Try again or type -1 to cancel:");
    }
}
