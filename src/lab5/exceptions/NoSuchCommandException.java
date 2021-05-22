package lab5.exceptions;

import lab5.console.InputManager;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String details) {
        super(InputManager.isConsoleInput()
                ? "Incorrect command (" + details + "). Type help to get a list of all available commands"
                : "Incorrect command (" + details + ")");
    }
}
