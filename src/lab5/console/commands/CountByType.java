package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketType;

public class CountByType extends Command {
    protected CountByType() {
        super("count_by_type", "count_by_type description", "count_by_type type");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            TicketType ticketType = TicketType.valueOf(argument.toUpperCase());
            int count = 0;

            for (Ticket ticket : CollectionManager.getTickets()) if (ticket.getType().equals(ticketType)) count++;

            System.out.println("Elements found: " + count);
        } catch (IllegalArgumentException e) {
            throw new IncorrectArgumentException(argument + " is not a valid type");
        }
    }
}