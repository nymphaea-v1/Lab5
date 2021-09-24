package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;
    private final TicketReader ticketReader;

    public Update(CollectionManager collectionManager, InputReader inputReader, TicketReader ticketReader) {
        super("update", "update element with specified id", "update id");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
        this.ticketReader = ticketReader;
    }

    @Override
    public void execute(String idString) throws IncorrectArgumentException, CancelCommandException {
        if (idString == null) throw new IncorrectArgumentException("no argument");

        if (collectionManager.getSize() == 0) {
            System.out.println("Collection is empty");
            return;
        }

        long id;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(idString + " is not a valid id");
        }

        Ticket ticket = collectionManager.getElementById(id);

        if (ticket == null) {
            throw new IncorrectArgumentException("no elements with specified id found (" + idString + ")");
        }

        ticketReader.updateTicket(inputReader, ticket);

        System.out.printf("Element with id %d has been updated%n", id);
    }
}
