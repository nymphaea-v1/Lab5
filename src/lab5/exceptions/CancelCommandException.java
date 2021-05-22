package lab5.exceptions;

public class CancelCommandException extends RuntimeException {
    public CancelCommandException(String message) {
        super("Current command execution has been cancelled due to incorrect input (" + message + ")");
    }

    public CancelCommandException() {
        super("Current command execution has been cancelled by user");
    }
}
