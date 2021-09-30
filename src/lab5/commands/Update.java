package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

/**
 * Complex command with an argument.
 * Updates an element of the collection with the specified id.
 *
 * @see lab5.commands.Command
 * @see lab5.CommandManager
 * @see CollectionManager
 * @see InputReader
 */

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public Update(CollectionManager collectionManager, InputReader inputReader) {
        super("update", "update element with specified id", "update id");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String idString) throws IncorrectArgumentException {
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

        TicketReader.updateTicket(inputReader, ticket);

        System.out.printf("Element with id %d has been updated%n", id);
    }
}
