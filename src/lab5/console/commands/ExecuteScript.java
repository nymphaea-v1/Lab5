package lab5.console.commands;

import lab5.FileManager;
import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.IncorrectScriptException;
import lab5.exceptions.NoSuchCommandException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ExecuteScript extends Command {

    protected ExecuteScript() {
        super("execute_script", "execute_script description", "execute_script file_path");
    }

    @Override
    protected void execute(String filePath) throws IncorrectArgumentException {
        if (filePath == null) throw new IncorrectArgumentException("no argument");

        try {
            ArrayList<String> script = FileManager.readFile(filePath);
            CommandManager.executeScript(script, filePath);
        } catch (FileNotFoundException e) {
            throw new IncorrectArgumentException("cannot find specified file (" + filePath + ")");
        } catch (NoSuchCommandException | IncorrectArgumentException | IncorrectScriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
