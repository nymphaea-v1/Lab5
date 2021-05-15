package lab5.exceptions;

public class RepeatingException extends Exception {
    public RepeatingException(String details) {
        super("Element with " + details + " has already been added to the collection");
    }
}
