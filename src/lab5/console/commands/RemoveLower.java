package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.TicketManager;

import java.util.Set;

public class RemoveLower extends SimpleCommand {
    protected RemoveLower() {
        super("remove_lower", "remove_lower description");
    }

    @Override
    protected void execute() {
        try {
            Set<Integer> keySetToRemove = CollectionManager.filterByTicketBoundary(TicketManager.createTicket(TicketManager.readTicketFields()), false);

            if (keySetToRemove.isEmpty()) {
                System.out.println("There are no lower elements");
                return;
            }

            CollectionManager.removeElements(keySetToRemove);
            System.out.println(keySetToRemove.size() + " elements was removed");
        } catch (CancelCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
