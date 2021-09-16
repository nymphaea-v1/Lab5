package lab5.commands;

import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;

import java.io.FileNotFoundException;

public class ExecuteScript extends Command {

    public ExecuteScript() {
        super("execute_script", "execute script from specified file", "execute_script file_path");
    }

    @Override
    public void execute(String filePath) throws IncorrectArgumentException {
        if (filePath == null) throw new IncorrectArgumentException("no argument");

        try {
            InputReader.addFileToScan(filePath);
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException("file not found (" + filePath + ")");
        }
    }
}
