package lab5;

import lab5.commands.*;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.NoSuchCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.TicketReader;

import java.util.*;

public class CommandManager {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public Collection<Command> getCommands() {
        return commandMap.values();
    }

    public CommandManager(CollectionManager collectionManager, InputReader inputReader, TicketReader ticketReader) {
        setCommand(new Help(commandMap.values()));
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

        setCommand(new RemoveGreater(collectionManager, inputReader, ticketReader));
        setCommand(new RemoveLower(collectionManager, inputReader, ticketReader));
        setCommand(new RemoveAnyByPerson(collectionManager, inputReader, ticketReader));

        setCommand(new Update(collectionManager, inputReader, ticketReader));
        setCommand(new Insert(collectionManager, inputReader, ticketReader));
    }

    public void execute(String commandName, String argument) throws CancelCommandException, IncorrectArgumentException, NoSuchCommandException {
        Command command = commandMap.get(commandName);
        if (command == null) throw new NoSuchCommandException();
        command.execute(argument);
    }

    private void setCommand(Command command) {
        commandMap.put(command.getName(), command);
    }
}