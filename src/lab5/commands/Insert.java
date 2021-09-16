package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.TicketReader;

public class Insert extends Command {
    public Insert() {
        super("insert", "insert new element with specified key", "insert key");
    }

    @Override
    public void execute(String argument) throws IncorrectArgumentException, CancelCommandException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        Integer key;
        try {
            key = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }

        if (CollectionManager.containsKey(key)) {
            throw new IncorrectArgumentException(argument + " is not a valid key");
        }

        CollectionManager.setElement(key, TicketReader.readTicket());
        System.out.println("Ticket has been added to the collection");
    }
}