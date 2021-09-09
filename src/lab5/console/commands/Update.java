package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;

import lab5.ticket.TicketReader;

public class  Update extends Command {
    protected Update() {
        super("update", "update description", "update id");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException, CancelCommandException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid id");
        }

        Integer key = CollectionManager.getKeyById(id);

        if (key == null) throw new IncorrectArgumentException("no elements with specified id found (" + argument + ")");

        CollectionManager.setElement(key, TicketReader.readTicket());

        System.out.printf("Element with id %d has been updated%n", id);
    }
}
