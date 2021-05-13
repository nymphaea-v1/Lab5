package lab5.console.commands;

import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.NoSuchCommandException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    protected static Collection<Command> getCommandCollection() {
        return commandMap.values();
    }

    public static void setCommands() {
        setCommand(new Help());
        setCommand(new Info());
        setCommand(new Show());
        setCommand(new Clear());
        setCommand(new Exit());

        setCommand(new RemoveKey());
        setCommand(new RemoveGreater());
    }

    public static void executeCommand(String commandString) throws NoSuchCommandException {
        String[] decodedCommand = decodeCommand(commandString);
        Command command = commandMap.get(decodedCommand[0]);
        String argument = decodedCommand[1];

        if (command instanceof ComplexCommand) {
            executeComplexCommand((ComplexCommand) command, argument);
        } else ((SimpleCommand) command).execute();
    }

    private static void executeComplexCommand(ComplexCommand command, String argument) {
        try {
            command.execute(argument);
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());

            String newParameters = new Scanner(System.in).nextLine();
            if (newParameters.equals("-1")) {
                System.out.println("Current command execution was stopped");
                return;
            }
            executeComplexCommand(command, newParameters.equals("") ? null : newParameters);
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
        } else {
            commandStr = input.toLowerCase();
        }

        if (!commandMap.containsKey(commandStr)) throw new NoSuchCommandException(commandStr);

        return new String[] {commandStr, argument};
    }
}
