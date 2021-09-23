package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;

import java.io.FileNotFoundException;

public class ExecuteScript extends Command {
    private final InputReader inputReader;

    public ExecuteScript(InputReader inputReader) {
        super("execute_script", "execute script from specified file", "execute_script file_path");
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String filePath, CollectionManager collectionManager) throws IncorrectArgumentException {
        if (filePath == null) throw new IncorrectArgumentException("no argument");

        try {
            inputReader.addToScan(filePath);
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException(e.getMessage());
        }
    }
}
