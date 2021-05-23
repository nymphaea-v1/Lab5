package lab5.console.commands;

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

    public static void execute(String commandString) throws NoSuchCommandException {
        String[] decodedCommand = decodeCommand(commandString);
        Command command = commandMap.get(decodedCommand[0]);
        String argument = decodedCommand[1];

        try {
            execute(command, argument);
        } catch (CancelCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void execute(Command command, String argument) throws CancelCommandException {
        while (true) {
            try {
                command.execute(argument);
                return;
            }  catch (IncorrectArgumentException e) {
                argument = CommandReader.processIncorrectInput(e.getMessage());
            }
        }
    }

    private static void setCommand(Command command) {
        commandMap.put(command.getCode(), command);
    }

    public static String[] decodeCommand(String input) throws NoSuchCommandException {
        input = input.trim();
        int spaceIndex = input.indexOf(" ");
        String commandStr;
        String argument = null;

        if (spaceIndex != -1) {
            commandStr = input.substring(0, spaceIndex).toLowerCase();
            argument = input.substring(spaceIndex+1);
        } else commandStr = input.toLowerCase();

        if (!commandMap.containsKey(commandStr)) throw new NoSuchCommandException(commandStr);

        return new String[] {commandStr, argument};
    }
}