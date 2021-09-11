package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "remove_greater description");
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Ticket ticket = TicketReader.readTicket();
        int sizeBefore = CollectionManager.getSize();

        CollectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) > 0);

        System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
    }
}
