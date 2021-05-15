package lab5.exceptions;

public class CancelCommandException extends Exception {
    public CancelCommandException() {
        super("Current command execution was cancelled");
    }
}
