package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class RemoveGreater extends Command {
    private final InputReader inputReader;

    public RemoveGreater(InputReader inputReader) {
        super("remove_greater", "remove all greater elements");
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String argument, CollectionManager collectionManager) throws CancelCommandException {
        Ticket ticket = TicketReader.readTicket(inputReader);
        int sizeBefore = collectionManager.getSize();

        collectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) > 0);

        System.out.println("Elements removed: " + (sizeBefore - collectionManager.getSize()));
    }
}
