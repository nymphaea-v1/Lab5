package lab5.commands;

import lab5.InputReader;

/**
 * Basic command.
 * Stops reading input commands.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see InputReader
 */

public class Exit extends Command {
    private final InputReader inputReader;

    public Exit(InputReader inputReader) {
        super("exit", "exit the program (without saving)");
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument) {
        inputReader.stopReading();
    }
}
