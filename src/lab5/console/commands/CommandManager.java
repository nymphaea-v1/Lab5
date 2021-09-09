package lab5.console.commands;

import lab5.console.commands.*;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.NoSuchCommandException;
import lab5.exceptions.IncorrectArgumentException;

import java.util.*;

public class CommandManager {
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    protected static Collection<Command> getCommands() {
        return commandMap.values();
    }

    public static void setCommands() {
        setCommand(new Help());
        setCommand(new Info());
        setCommand(new Show());
        setCommand(new Clear());
        setCommand(new Save());
        setCommand(new Exit());

        setCommand(new RemoveKey());
        setCommand(new RemoveLowerKey());
        setCommand(new CountByType());
        setCommand(new FilterStartsWithName());
        setCommand(new ExecuteScript());

        setCommand(new RemoveGreater());
        setCommand(new RemoveLower());
        setCommand(new RemoveAnyByPerson());

        setCommand(new Update());
        setCommand(new Insert());
    }

    public static void execute(String commandStr, String argument) throws CancelCommandException, IncorrectArgumentException, NoSuchCommandException {
        if (!commandMap.containsKey(commandStr)) throw new NoSuchCommandException(commandStr);
        commandMap.get(commandStr).execute(argument);
    }
    private static void setCommand(Command command) {
        commandMap.put(command.getCode(), command);
    }
}