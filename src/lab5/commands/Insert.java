package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.TicketReader;

/**
 * Complex command with an argument.
 * Inserts the new element with the specified key in the collection.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 * @see InputReader
 */

public class Insert extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public Insert(CollectionManager collectionManager, InputReader inputReader) {
        super("insert", "insert new element with specified key", "insert key");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String keyString) throws IncorrectArgumentException {
        if (keyString == null) throw new IncorrectArgumentException("no argument");

        Long key;
        try {
            key = Long.parseLong(keyString);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(keyString + " is not a valid key");
        }

        if (collectionManager.containsKey(key)) {
            throw new IncorrectArgumentException(keyString + " is not a valid key");
        }

        collectionManager.setElement(key, TicketReader.readNewTicket(inputReader, collectionManager.getNextId()));
        System.out.println("Ticket has been added to the collection");
    }
}
