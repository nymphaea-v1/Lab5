package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketType;

public class CountByType extends Command {
    private final CollectionManager collectionManager;

    public CountByType(CollectionManager collectionManager) {
        super("count_by_type", "display the number of elements with specified ticket type", "count_by_type type");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String typeString) throws IncorrectArgumentException {
        if (typeString == null) throw new IncorrectArgumentException("no argument");

        TicketType type;
        try {
            type = TicketType.valueOf(typeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectArgumentException(typeString + " is not a valid type");
        }

        int count = 0;
        for (Ticket ticket : collectionManager.getValues()) if (ticket.getType().equals(type)) count++;

        System.out.println("Elements found: " + count);
    }
}
