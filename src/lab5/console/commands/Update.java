package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectInputException;
import lab5.ticket.TicketManager;

public class Update extends ComplexCommand {
    protected Update() {
        super("update", "update description", "update id");
    }

    @Override
    protected void execute(String argument) throws IncorrectInputException, CancelCommandException {
        try {
            long id = Long.parseLong(argument);
            Integer key = CollectionManager.getKeyById(id);

            if (key == null) {
                System.out.println("No elements with that id found");
                return;
            }

            CollectionManager.setElement(key, TicketManager.createTicket(TicketManager.readTicketFields()));
            System.out.printf("Element with id %d was updated%n", id);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IncorrectInputException(argument + " is not a valid id");
        }
    }
}
