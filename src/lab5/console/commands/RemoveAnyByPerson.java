package lab5.console.commands;

import lab5.CollectionManager;
import lab5.exceptions.CancelCommandException;
import lab5.ticket.Person;
import lab5.ticket.Ticket;
import lab5.ticket.TicketManager;

import java.util.Map;

public class RemoveAnyByPerson extends Command {
    protected RemoveAnyByPerson() {
        super("remove_any_by_person", "remove_any_by_person description");
    }

    @Override
    protected void execute(String argument) throws CancelCommandException {
        Person person = TicketManager.createPerson(TicketManager.readPersonFields());

        for (Map.Entry<Integer, Ticket> entry : CollectionManager.getEntrySet()) {
            if (entry.getValue().getPerson().equals(person)) {
                CollectionManager.removeElement(entry.getKey());
                System.out.println("One element with that person has been removed");

                return;
            }
        }

        System.out.println("There are no elements with specified person");
    }
}
