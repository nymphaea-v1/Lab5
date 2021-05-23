package lab5.exceptions;

import lab5.console.commands.CommandReader;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String details) {
        super(CommandReader.fromConsole
                ? "Incorrect command (" + details + "). Type help to get a list of all available commands"
                : "Incorrect command (" + details + ")");
    }
}
