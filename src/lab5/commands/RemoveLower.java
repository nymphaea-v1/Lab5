package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_lower", "remove all lower elements");
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Ticket ticket;
        try {
            ticket = TicketReader.read(true);
        } catch (InputReader.CannotReadObjectException e) {
            throw new CancelCommandException(e.getMessage());
        }
        int sizeBefore = CollectionManager.getSize();

        CollectionManager.getEntrySet().removeIf(n -> n.getValue().compareTo(ticket) < 0);

        System.out.println("Elements removed: " + (sizeBefore - CollectionManager.getSize()));
    }
}
