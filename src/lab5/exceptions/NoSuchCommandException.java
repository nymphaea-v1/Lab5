package lab5.exceptions;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String details) {
        super("Incorrect command (" + details + "). Type help to get a list of all available commands");
    }
}
