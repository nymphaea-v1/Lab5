package lab5.exceptions;

public class IncorrectFileException extends Exception {
    public IncorrectFileException(String details) {
        super("Incorrect file: " + details);
    }
}