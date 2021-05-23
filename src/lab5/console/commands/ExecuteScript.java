package lab5.console.commands;

import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.IncorrectScriptException;

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
            CommandReader.addFile(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException("cannot find specified file (" + filePath + ")");
        } catch (IncorrectScriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
