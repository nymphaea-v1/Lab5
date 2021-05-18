package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.IncorrectScriptException;
import lab5.ticket.TicketManager;

public class Update extends Command {
    protected Update() {
        super("update", "update description", "update id");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException, CancelCommandException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            long id = Long.parseLong(argument);
            Integer key = CollectionManager.getKeyById(id);

            if (key == null) {
                System.out.println("No elements with specified id found");
                return;
            }

            if (!CollectionManager.setElement(key, TicketManager.createTicket(TicketManager.readTicketFields()))) {
                throw new IncorrectArgumentException("ticket fields");
            }

            System.out.printf("Element with id %d has been updated%n", id);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException(argument + " is not a valid id");
        } catch (IncorrectScriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
