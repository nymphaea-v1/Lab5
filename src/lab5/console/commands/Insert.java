package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.UnreadableInputException;
import lab5.exceptions.CancelCommandException;

import lab5.ticket.TicketManager;

public class Insert extends ComplexCommand {
    protected Insert() {
        super("insert", "insert description", "insert key");
    }

    @Override
    protected void execute(String argument) throws UnreadableInputException {
        Integer key;

        try {
            key = Integer.parseInt(argument);
            if (CollectionManager.containsKey(key)) throw new UnreadableInputException("key must be a unique positive integer");
        } catch (NumberFormatException e) {
            throw new UnreadableInputException("key must be a unique positive integer");
        }

        try {
            CollectionManager.setElement(key, TicketManager.createTicket(TicketManager.readTicketFields()));
            System.out.println("Ticket was created and added to collection");
        } catch (CancelCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
