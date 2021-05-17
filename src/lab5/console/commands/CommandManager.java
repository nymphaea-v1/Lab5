package lab5.console.commands;

import lab5.console.ConsoleManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.NoSuchCommandException;
import lab5.exceptions.IncorrectInputException;

import java.util.Collection;
import java.util.HashMap;

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

        setCommand(new Update());
        setCommand(new RemoveGreater());
        setCommand(new RemoveLower());
        setCommand(new RemoveAnyByPerson());

        setCommand(new Insert());
    }

    public static void executeCommand(String commandString) throws NoSuchCommandException {
        String[] decodedCommand = decodeCommand(commandString);
        Command command = commandMap.get(decodedCommand[0]);
        String argument = decodedCommand[1];

        try {
            if (command instanceof ComplexCommand) executeComplexCommand((ComplexCommand) command, argument);
            else ((SimpleCommand) command).execute();
        } catch (CancelCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeComplexCommand(ComplexCommand command, String argument) throws CancelCommandException {
        try {
            command.execute(argument);
        } catch (IncorrectInputException e) {
            System.out.println(e.getMessage());

            String newParameters = ConsoleManager.read();
            if (newParameters != null && newParameters.equals("-1")) throw new CancelCommandException();

            executeComplexCommand(command, newParameters);
        }
    }

    private static void setCommand(Command command) {
        commandMap.put(command.getName(), command);
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