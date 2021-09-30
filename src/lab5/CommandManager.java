package lab5;

import lab5.commands.*;
import lab5.exceptions.NoSuchCommandException;
import lab5.exceptions.IncorrectArgumentException;

import java.util.*;

/**
 * This class represents a manager that creates a set of commands connected to the specified collection
 * and allows user to manage it with given commands.
 */

public class CommandManager {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public Collection<Command> getCommands() {
        return commandMap.values();
    }

    /**
     * Sets a list of commands and creates command manager.
     *
     * @param collectionManager manager of the collection that gives access for commands to collection management
     * @param inputReader input reader that gives access for complex commands to reading methods
     */

    public CommandManager(CollectionManager collectionManager, InputReader inputReader) {
        setCommand(new Help(this));
        setCommand(new Info(collectionManager));
        setCommand(new Show(collectionManager));
        setCommand(new Clear(collectionManager));
        setCommand(new Save(collectionManager));
        setCommand(new Exit(inputReader));

        setCommand(new RemoveKey(collectionManager));
        setCommand(new RemoveLowerKey(collectionManager));
        setCommand(new CountByType(collectionManager));
        setCommand(new FilterStartsWithName(collectionManager));
        setCommand(new ExecuteScript(inputReader));

        setCommand(new RemoveGreater(collectionManager, inputReader));
        setCommand(new RemoveLower(collectionManager, inputReader));
        setCommand(new RemoveAnyByPerson(collectionManager, inputReader));

        setCommand(new Update(collectionManager, inputReader));
        setCommand(new Insert(collectionManager, inputReader));
    }

    /**
     * Executes the specified command by it's name.
     *
     * @param commandName name of the command to be executed
     * @param argument argument of the command
     * @throws IncorrectArgumentException thrown if an argument is not correct
     * @throws NoSuchCommandException thrown if there is no command with the specified name
     */

    public void execute(String commandName, String argument) throws IncorrectArgumentException, NoSuchCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) throw new NoSuchCommandException();
        command.execute(argument);
    }

    private void setCommand(Command command) {
        commandMap.put(command.getName(), command);
    }
}