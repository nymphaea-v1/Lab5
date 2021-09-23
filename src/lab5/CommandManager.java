package lab5;

import lab5.commands.*;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.NoSuchCommandException;
import lab5.exceptions.IncorrectArgumentException;

import java.util.*;

public class CommandManager {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    private final CollectionManager collectionManager;

    public Collection<Command> getCommands() {
        return commandMap.values();
    }

    public CommandManager(CollectionManager collectionManager, InputReader inputReader) {
        this.collectionManager = collectionManager;

        setCommand(new Help(commandMap.values()));
        setCommand(new Info());
        setCommand(new Show());
        setCommand(new Clear());
        setCommand(new Save());
        setCommand(new Exit(inputReader));

        setCommand(new RemoveKey());
        setCommand(new RemoveLowerKey());
        setCommand(new CountByType());
        setCommand(new FilterStartsWithName());
        setCommand(new ExecuteScript(inputReader));

        setCommand(new RemoveGreater(inputReader));
        setCommand(new RemoveLower(inputReader));
        setCommand(new RemoveAnyByPerson(inputReader));

        setCommand(new Update(inputReader));
        setCommand(new Insert(inputReader));
    }

    public void execute(String commandName, String argument) throws CancelCommandException, IncorrectArgumentException, NoSuchCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) throw new NoSuchCommandException();
        command.execute(argument, collectionManager);
    }

    private void setCommand(Command command) {
        commandMap.put(command.getName(), command);
    }
}