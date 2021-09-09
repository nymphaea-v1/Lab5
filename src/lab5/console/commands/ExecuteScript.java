package lab5.console.commands;

import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;

import java.io.File;
import java.io.FileNotFoundException;

public class ExecuteScript extends Command {

    protected ExecuteScript() {
        super("execute_script", "execute_script description", "execute_script file_path");
    }

    @Override
    protected void execute(String filePath) throws IncorrectArgumentException {
        if (filePath == null) throw new IncorrectArgumentException("no argument");

        try {
            InputReader.addFileToScan(new File(filePath), "\n");
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException("file not found (" + filePath + ")");
        }
    }
}
