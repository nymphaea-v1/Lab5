package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

public class RemoveLower extends SimpleCommand {
    protected RemoveLower() {
        super("remove_lower", "remove_lower description");
    }

    @Override
    protected void execute() throws CancelCommandException {
        Ticket ticket = TicketManager.createTicket(TicketManager.readTicketFields());
        int sizeBefore = CollectionManager.getSize();

        CollectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) < 0);

        System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
    }
}
