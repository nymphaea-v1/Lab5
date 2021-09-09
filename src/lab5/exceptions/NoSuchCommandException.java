package lab5.exceptions;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String details) {
        super( "Incorrect command (" + details + ")");
    }
}
