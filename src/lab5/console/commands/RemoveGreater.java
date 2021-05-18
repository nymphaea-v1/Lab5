package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectScriptException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

public class RemoveGreater extends Command {
    protected RemoveGreater() {
        super("remove_greater", "remove_greater description");
    }

    @Override
    protected void execute(String argument) throws CancelCommandException {
        try {
            Ticket ticket = TicketManager.createTicket(TicketManager.readTicketFields());
            int sizeBefore = CollectionManager.getSize();

            CollectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) > 0);

            System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
        } catch (IncorrectScriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
