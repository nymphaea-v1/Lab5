package lab5.exceptions;

import lab5.console.InputManager;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String details) {
        super("Incorrect argument (" + details + (InputManager.isConsoleInput() ? "). Try again or type -1 to cancel:" : ")."));
    }
}
