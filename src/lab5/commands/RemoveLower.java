package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public RemoveLower(CollectionManager collectionManager, InputReader inputReader) {
        super("remove_lower", "remove all lower elements");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Ticket ticket = TicketReader.readNewTicket(inputReader, collectionManager.getNextId());
        int sizeBefore = collectionManager.getSize();

        collectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) < 0);

        System.out.println("Elements removed: " + (sizeBefore - collectionManager.getSize()));
    }
}
