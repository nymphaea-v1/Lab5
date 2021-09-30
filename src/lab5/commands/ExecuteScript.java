package lab5.commands;

import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;

import java.io.FileNotFoundException;

/**
 * Basic command with an argument.
 * Executes the script from the specified file.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see InputReader
 */

public class ExecuteScript extends Command {
    private final InputReader inputReader;

    public ExecuteScript(InputReader inputReader) {
        super("execute_script", "execute script from specified file", "execute_script file_path");
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String filePath) throws IncorrectArgumentException {
        if (filePath == null) throw new IncorrectArgumentException("no argument");

        try {
            inputReader.addToScan(filePath);
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException(e.getMessage());
        }
    }
}
