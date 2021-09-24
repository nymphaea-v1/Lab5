package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;
    private final TicketReader ticketReader;

    public RemoveGreater(CollectionManager collectionManager, InputReader inputReader, TicketReader ticketReader) {
        super("remove_greater", "remove all greater elements");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
        this.ticketReader = ticketReader;
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Ticket ticket = ticketReader.readNewTicket(inputReader);
        int sizeBefore = collectionManager.getSize();

        collectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) > 0);

        System.out.println("Elements removed: " + (sizeBefore - collectionManager.getSize()));
    }
}
