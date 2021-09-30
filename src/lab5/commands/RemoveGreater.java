package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

/**
 * Complex command.
 * Removes all elements that greater than the given one.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 * @see InputReader
 */

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public RemoveGreater(CollectionManager collectionManager, InputReader inputReader) {
        super("remove_greater", "remove all greater elements");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument) {
        Ticket ticket = TicketReader.readNewTicket(inputReader, collectionManager.getNextId());
        int sizeBefore = collectionManager.getSize();

        collectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) > 0);

        System.out.println("Elements removed: " + (sizeBefore - collectionManager.getSize()));
    }
}
