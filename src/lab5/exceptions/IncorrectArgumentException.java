package lab5.exceptions;

import lab5.console.InputManager;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String details) {
        super(InputManager.isConsoleInput()
                ? "Incorrect argument (" + details + "). Try again or type -1 to cancel:"
                : details);
    }
}
