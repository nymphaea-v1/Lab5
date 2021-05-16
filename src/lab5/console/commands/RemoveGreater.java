package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.util.Set;

public class RemoveGreater extends SimpleCommand {
    protected RemoveGreater() {
        super("remove_greater", "remove_greater description");
    }

    @Override
    protected void execute() throws CancelCommandException {
        Ticket ticket = TicketManager.createTicket(TicketManager.readTicketFields());
        Set<Integer> keySetToRemove = CollectionManager.getKeySet(n -> n.getValue().compareTo(ticket) > 0);

        if (keySetToRemove.isEmpty()) {
            System.out.println("There are no greater elements");
            return;
        }

        CollectionManager.removeElements(keySetToRemove);
        System.out.println(keySetToRemove.size() + " elements was removed");
    }
}
