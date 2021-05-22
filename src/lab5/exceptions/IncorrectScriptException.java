package lab5.exceptions;

public class IncorrectScriptException extends Exception {
    public IncorrectScriptException(String details) {
        super("Incorrect script (" + details + ")");
    }
}
