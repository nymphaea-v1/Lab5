package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.IncorrectArgumentException;
import lab5.ticket.Ticket;

import java.util.ArrayList;
import java.util.Collection;

public class FilterStartsWithName extends Command {
    protected FilterStartsWithName() {
        super("filter_starts_with_name", "filter_starts_with_name description", "filter_starts_with_name name");
    }

    @Override
    protected void execute(String argument) throws IncorrectArgumentException {
        if (argument == null) throw new IncorrectArgumentException("no argument");

        Collection<Ticket> tickets = new ArrayList<>();

        for (Ticket ticket : CollectionManager.getTickets()) if (ticket.getName().startsWith(argument)) tickets.add(ticket);

        System.out.println("Elements found: " + tickets.size());
        tickets.forEach(System.out::println);
    }
}
