package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.util.HashSet;
import java.util.Set;

public class RemoveGreater extends SimpleCommand {
    protected RemoveGreater() {
        super("remove_greater", "remove_greater description");
    }

    @Override
    protected void execute() throws CancelCommandException {
        Ticket ticket = TicketManager.createTicket(TicketManager.readTicketFields());
        Set<Integer> keySet = new HashSet<>();

        CollectionManager.getEntrySet().stream()
                .filter(n -> n.getValue().compareTo(ticket) > 0)
                .forEach(n -> keySet.add(n.getKey()));

        if (keySet.isEmpty()) {
            System.out.println("There are no greater elements");
        } else {
            CollectionManager.removeElements(keySet);
            System.out.println(keySet.size() + " elements was removed");
        }
    }
}
