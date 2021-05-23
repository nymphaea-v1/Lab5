package lab5.exceptions;

import lab5.console.commands.CommandReader;

public class IncorrectArgumentException extends Exception {
    public IncorrectArgumentException(String details) {
        super(CommandReader.fromConsole
                ? "Incorrect argument (" + details + "). Try again or type -1 to cancel:"
                : details);
    }
}
