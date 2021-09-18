package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class Update extends Command {
    public Update() {
        super("update", "update element with specified id", "update id");
    }

    @Override
    public void execute(String argument) throws IncorrectArgumentException, CancelCommandException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        if (CollectionManager.getSize() == 0) {
            System.out.println("Collection is empty");
            return;
        }

        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid id");
        }

        Ticket ticket = CollectionManager.getElementById(id);

        if (ticket == null) {
            throw new IncorrectArgumentException("no elements with specified id found (" + argument + ")");
        }

        TicketReader.updateTicket(ticket);

        System.out.printf("Element with id %d has been updated%n", id);
    }
}
