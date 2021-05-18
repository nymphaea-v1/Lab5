package lab5.exceptions;

public class CancelCommandException extends RuntimeException {
    public CancelCommandException() {
        super("Current command execution has been cancelled");
    }
}
