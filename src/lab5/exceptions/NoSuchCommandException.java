package lab5.exceptions;

import lab5.console.InputManager;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String details) {
        super("Incorrect command (" + details + (InputManager.isConsoleInput() ? "). Type help to get a list of all available commands" : ")."));
    }
}
