package lab5.commands;

import lab5.CollectionManager;
import lab5.InputReader;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.TicketReader;

public class Insert extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;
    private final TicketReader ticketReader;

    public Insert(CollectionManager collectionManager, InputReader inputReader, TicketReader ticketReader) {
        super("insert", "insert new element with specified key", "insert key");
        this.collectionManager = collectionManager;
        this.inputReader = inputReader;
        this.ticketReader = ticketReader;
    }

    @Override
    public void execute(String keyString) throws IncorrectArgumentException, CancelCommandException {
        if (keyString == null) throw new IncorrectArgumentException("no argument");

        Long key;
        try {
            key = Long.parseLong(keyString);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(keyString + " is not a valid key");
        }

        if (collectionManager.containsKey(key)) {
            throw new IncorrectArgumentException(keyString + " is not a valid key");
        }

        collectionManager.setElement(key, ticketReader.readNewTicket(inputReader));
        System.out.println("Ticket has been added to the collection");
    }
}
