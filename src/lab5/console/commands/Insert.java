package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;
import lab5.exceptions.CancelCommandException;

import lab5.exceptions.IncorrectScriptException;
import lab5.ticket.TicketManager;

public class Insert extends Command {
    protected Insert() {
        super("insert", "insert description", "insert key");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        try {
            Integer key = Integer.parseInt(argument);

            if (CollectionManager.containsKey(key)) {
                throw new IncorrectArgumentException("key must be a unique positive integer");
            }

            if (!CollectionManager.setElement(key, TicketManager.createTicket(TicketManager.readTicketFields()))) {
                throw new IncorrectArgumentException("ticket fields");
            }

            System.out.println("Ticket has been added to the collection");
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentException("key must be a unique positive integer");
        } catch (CancelCommandException | IncorrectScriptException e) {
            System.out.println(e.getMessage());
        }
    }
}
