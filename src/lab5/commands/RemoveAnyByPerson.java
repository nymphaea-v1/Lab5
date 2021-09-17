package lab5.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Person;
import lab5.ticket.Ticket;
import lab5.ticket.TicketReader;

import java.util.Map;

public class RemoveAnyByPerson extends Command {
    public RemoveAnyByPerson() {
        super("remove_any_by_person", "remove one element with specified person");
    }

    @Override
    public void execute(String argument) throws CancelCommandException {
        Person person = TicketReader.readPerson();

        for (Map.Entry<Long, Ticket> entry : CollectionManager.getEntrySet()) {
            if (!(entry.getValue().getPerson().equals(person))) continue;

            CollectionManager.removeElement(entry.getKey());
            System.out.println("One element with that person has been removed");

            return;
        }

        System.out.println("There are no elements with specified person");
    }
}
